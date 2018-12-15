package fastfood.service;

import fastfood.domain.dtos.ItemImportDto;
import fastfood.domain.entities.Category;
import fastfood.domain.entities.Item;
import fastfood.repository.CategoryRepository;
import fastfood.repository.ItemRepository;
import fastfood.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void importItems(ItemImportDto[] itemImportDtos) {

        for(ItemImportDto itemImportDto : itemImportDtos){
            if(!this.validationUtil.isValid(itemImportDto)){
                System.out.println("Error: Invalid data.");
                continue;
            }

            Item item = this.itemRepository.findByName(itemImportDto.getName())
                    .orElse(null);

            Category category = this.categoryRepository.findByName(itemImportDto.getCategory())
                    .orElse(null);

            if(category == null){
                category = new Category();
                category.setName(itemImportDto.getCategory());
                this.categoryRepository.saveAndFlush(category);
            }

            if(item == null){
                item = this.modelMapper.map(itemImportDto, Item.class);
                item.setCategory(category);
                this.itemRepository.saveAndFlush(item);
            }
        }
    }
}
