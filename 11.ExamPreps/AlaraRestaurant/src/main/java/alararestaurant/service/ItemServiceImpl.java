package alararestaurant.service;

import alararestaurant.constants.Constants;
import alararestaurant.domain.dtos.ItemImportJsonDto;
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

    private final static String ITEMS_FILE_CONTENT =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\11.ExamPreps\\AlaraRestaurant\\src\\main\\resources\\files\\items.json";

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository, FileUtil fileUtil, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
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

        ItemImportJsonDto[] itemImportJsonDtos =
                this.gson.fromJson(items, ItemImportJsonDto[].class);

        for(ItemImportJsonDto itemImportJsonDto : itemImportJsonDtos){
            if(!this.validationUtil.isValid(itemImportJsonDto)){
                importResult.append(Constants.INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());
                continue;
            }

            Category category = this.categoryRepository
                    .findByName(itemImportJsonDto.getCategory())
                    .orElse(null);

            if(category == null){
                category = new Category();
                category.setName(itemImportJsonDto.getCategory());
                this.categoryRepository.saveAndFlush(category);
            }

            Item item = this.itemRepository
                    .findByName(itemImportJsonDto.getName())
                    .orElse(null);

            if(item != null){
                importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            item = this.modelMapper.map(itemImportJsonDto, Item.class);
            item.setCategory(category);
            this.itemRepository.saveAndFlush(item);

            importResult
                    .append(String.format("Record %s successfully imported."
                            , item.getName()))
                    .append(System.lineSeparator());
        }

        return importResult.toString().trim();
    }
}
