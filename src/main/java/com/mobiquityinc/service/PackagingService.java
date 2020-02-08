package com.mobiquityinc.service;

import java.util.ArrayList;
import java.util.List;

import com.mobiquityinc.Entity.Item;
import com.mobiquityinc.Entity.Package;
import com.mobiquityinc.config.Configs;
import com.mobiquityinc.repository.IPackagingRepository;
import com.mobiquityinc.validator.IPackagingValidator;
/**
 * This class contains main algorithm how package is selected.
 * Only one singleton object is created.
 * @author Vuong Nguyen
 *
 */
public class PackagingService implements IPackagingService {
	
	private static PackagingService instance;
	private IPackagingRepository repository;
	private IPackagingValidator validator;
	private PackagingService(){};
	
	public static PackagingService getInstance(){
		if(instance==null){
			instance = new PackagingService();
		}
		return instance;
	}
    
	/**
	 * 
	 * @param absPath
	 *            {@link String} &nbsp; [ path to data file.
	 *                                   contain data to be parsed]
	 * @return {@link String} &nbsp; [the output]
	 */
	public String pack(String absPath) throws Exception {
		validator.validateFilePath(absPath);
		List<Package> records = repository.load(absPath);
		String s = records.stream().map(this::find).reduce((a,b)->(a+"\n"+b)).orElse("");
		return s;
	}
    
	/**
	 * 
	 * @param pack
	 *            {@link Package} &nbsp; [One package include maxWeight and list of items]
	 * @return @return {@link String} &nbsp; [the output]
	 */
	public String find(Package pack){
		List<List<Item>> groups = createPossibleGrouping(pack.getItems());
		List<Item> optPackage = selectOptPackage(groups,pack.getMaxWeight());
		return output(optPackage);
	}
	
	/**
	 * Create all possible combinations of items in one package.
	 * @param items
	 *            {@link List} &nbsp; [all items in one package]
	 * @return {@link List} &nbsp; [return all combinations of items]
	 */
	public  List<List<Item>> createPossibleGrouping(List<Item> items){
		List<List<Item>> groups = new ArrayList<List<Item>>();
		for(int x = 0; x < items.size(); x++){
			Item currentItem = items.get(x);
			int combinationSize = groups.size();
			for(int y = 0; y < combinationSize; y++){
				List<Item> combination = groups.get(y);
				List<Item> newCombination = new ArrayList<Item>(combination);
				newCombination.add(currentItem);
				groups.add(newCombination);
			}
			List<Item> current = new ArrayList<Item>();
			current.add(currentItem);
			groups.add(current);
		}
		
		return groups;
	}
	/**
	 * Select a group having best match of weight limit and highest cost
	 * @param groups
	 *            {@link List} &nbsp; [all groups in one package]
	 * @param limit
	 *            {@link Double} &nbsp; [max weight for one package]           
	 * @return {@link List} &nbsp; [return a best match group]
	 */
	public  List<Item> selectOptPackage(List<List<Item>> groups,double limit){
		List<Item> bestCombination = new ArrayList<Item>();
		if(groups==null) {
			return bestCombination;
		}
		double bestCost = 0;
		for(List<Item> combination : groups){
			double combinationWeight = getTotalWeight(combination);
			if(combinationWeight > limit || combination.size()>Configs.NUMBER_OF_ITEMS_TO_PICK_FROM){
				continue;
			}
			double combinationPrice = getTotalPrice(combination);
			if(combinationPrice > bestCost){
				bestCost = combinationPrice;
				bestCombination = combination;
			}
		
		}
		return bestCombination;
	}
	
	public  double getTotalWeight(List<Item> items){
		double total = 0;
		for(Item i : items){
			total += i.getWeight();
		}
		return total;
	}
	
	public  double getTotalPrice(List<Item> items){
		double total = 0;
		for(Item i : items){
			total += i.getCost();
		}
		return total;
	}
	
	public  String output(List<Item> items){
		if(items ==null || items.size() ==0) return "-";
		String res = "";
		for(Item item:items){
			res = res + item.getId()+ ",";
		}
		return res.substring(0, res.length()-1);
	}

	public IPackagingRepository getRepository() {
		return repository;
	}

	public void setRepository(IPackagingRepository repository) {
		this.repository = repository;
	}

	public IPackagingValidator getValidator() {
		return validator;
	}
	public void setValidator(IPackagingValidator validator) {
		this.validator = validator;
	}
	
	
	
}
