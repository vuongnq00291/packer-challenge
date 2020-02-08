package com.mobiquityinc.repository;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mobiquityinc.Entity.Item;
import com.mobiquityinc.Entity.Package;
import com.mobiquityinc.utils.LambdaExceptionUtil;
import com.mobiquityinc.utils.Utils;
import com.mobiquityinc.validator.IPackagingValidator;
/**
 * Load text and convert it to objects
 * @author vuong nguyen
 *
 */
public class PackagingRepository implements IPackagingRepository {

	private static IPackagingRepository instance;
	private IPackagingValidator validator;
	public static IPackagingRepository getInstance() {
		if (instance == null) {
			instance = new PackagingRepository();
		}
		return instance;
	}

	private PackagingRepository() {
	};
	
	/**
	 * input file parser and backing it into list of package
	 * 
	 * @return List<Package>
	 */
	public List<Package> load(String absPath) throws Exception {
		
		try (Stream<String> stream = Files.lines(Paths.get(absPath))) {
			List<Package> records = stream
					.map(LambdaExceptionUtil.rethrowFunction(PackagingRepository.getInstance()::parse))
					.collect(Collectors.toList());
			return records;
		}
		
		
	}
	/**
	 * 
	 * @param one
	 *            {@link String} &nbsp;[Single line to parse]
	 * @return {@link Package} &nbsp; [parsed package into items and total
	 *         weight]
	 * @throws Exception
	 */
	public Package parse(String line) throws Exception {
		validator.validateRow(line);
		String[] maxWeightAndItems = line.split(":");
		double maxWeight = Utils.convertToDouble(maxWeightAndItems[0]);
		validator.validateMaxWeight(line, maxWeight);
		String rawItem = maxWeightAndItems[1];
		String[] itemsInArray = rawItem.split(" ");
		
		List<Item> items = new ArrayList<Item>();
		for (String part : itemsInArray) {
			if (part.length() > 0) {
				String[] trunk = part.replace(")", "").replace("(", "").split(",");
				Item item = new Item(Utils.convertToInt(trunk[0]), Utils.convertToDouble(trunk[2].substring(1)),
						Utils.convertToDouble(trunk[1]));
				validator.validateItem(part, item);
				items.add(item);
			}
		}

		Package record = new Package();
		record.setItems(items);
		record.setMaxWeight(maxWeight);
		return record;
	}

	public IPackagingValidator getValidator() {
		return validator;
	}

	@Override
	public void setValidator(IPackagingValidator validator) {
		this.validator = validator;
	}

	
}
