package hiberspring.service;

import hiberspring.common.Constants;
import hiberspring.domain.dtos.products.ProductImportDto;
import hiberspring.domain.dtos.products.ProductImportRootDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Product;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.ProductRepository;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class ProductServiceImpl implements ProductService{

    private static final String PRODUCTS_FILE_CONTENT =
            Constants.PATH_TO_FILES + "products.xml";

    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;
    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;

    @Autowired
    public ProductServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil, XmlParser xmlParser, ProductRepository productRepository, BranchRepository branchRepository) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public Boolean productsAreImported() {
        return this.productRepository.count() > 0;
    }

    @Override
    public String readProductsXmlFile() throws IOException {
        return this.fileUtil.readFile(PRODUCTS_FILE_CONTENT);
    }

    @Override
    public String importProducts() throws JAXBException, FileNotFoundException {
        StringBuilder importResult = new StringBuilder();

        ProductImportRootDto productImportRootDto =
                this.xmlParser.parseXml(ProductImportRootDto.class, PRODUCTS_FILE_CONTENT);

        for(ProductImportDto productImportDto : productImportRootDto.getProductImportDtos()){

            Product product = this.productRepository
                    .findByName(productImportDto.getName())
                    .orElse(null);

            Branch branch = this.branchRepository
                    .findByName(productImportDto.getBranch())
                    .orElse(null);

            if(!this.validationUtil.isValid(productImportDto) || branch == null || product != null){
                importResult.append(Constants.INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());
                continue;
            }

            product = this.modelMapper.map(productImportDto, Product.class);
            product.setBranch(branch);
            this.productRepository.saveAndFlush(product);

            importResult.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,
                    product.getClass().getSimpleName(), product.getName()))
                    .append(System.lineSeparator());
        }

        return importResult.toString().trim();
    }
}
