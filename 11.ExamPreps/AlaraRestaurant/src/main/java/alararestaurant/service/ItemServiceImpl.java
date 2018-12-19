package alararestaurant.service;

import alararestaurant.constants.Constants;
import alararestaurant.domain.dtos.ItemImportDto;
import alararestaurant.domain.entities.Category;
import alararestaurant.domain.entities.Item;
import alararestaurant.repository.CategoryRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ItemServiceImpl implements ItemService {

    private static final String ITEMS_FILE_CONTENT =
            System.getProperty("user.dir") + "/src/main/resources/files/items.json";

    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ItemServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil, Gson gson, ItemRepository itemRepository, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Boolean itemsAreImported() {
        return this.itemRepository.count() > 0;
    }

    @Override
    public String readItemsJsonFile() throws IOException {
        return this.fileUtil.readFile(ITEMS_FILE_CONTENT);
    }

    @Override
    public String importItems(String items) {
        StringBuilder importResult = new StringBuilder();

        ItemImportDto[] itemImportDtos = this.gson.fromJson(items, ItemImportDto[].class);

        for(ItemImportDto itemImportDto : itemImportDtos){

            Item item = this.itemRepository
                    .findByName(itemImportDto.getName())
                    .orElse(null);

            if(!this.validationUtil.isValid(itemImportDto) || item != null){
                importResult.append(Constants.INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());
                continue;
            }

            Category category = this.categoryRepository
                    .findByName(itemImportDto.getCategory())
                    .orElse(null);

            if(category == null){
                category = new Category();
                category.setName(itemImportDto.getCategory());
                this.categoryRepository.saveAndFlush(category);
            }

            item = this.modelMapper.map(itemImportDto, Item.class);
            item.setCategory(category);
            this.itemRepository.saveAndFlush(item);

            importResult.append(String.format("Record %s successfully imported.", item.getName()))
                    .append(System.lineSeparator());
        }

        return importResult.toString().trim();
    }
}
