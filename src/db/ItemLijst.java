package db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import model.Item;
import model.ItemTypes;

public class ItemLijst {

	static List<Item> items = new ArrayList<Item>();
	
	/**
	 * Geeft gewoon de array met items terug
	 * @return
	 */
	public static List<Item> getItems()
	{
		return items;
	}
	
	/**
	 * Geeft alle items geformateerd en gestorteerd terug. Maakt gebruik van Java 8 Streams
	 * 
	 * @return
	 */
	public static String getItemsGeformateerd()
	{
		String output = "";
		
		Map<Object, List<Item>> itemMap = items.stream()
															.sorted((i1, i2) -> i1.getTitel().compareTo(i2.getTitel()))
															.collect(Collectors.groupingBy(i -> i.getClass().getSimpleName()));
		
		for (List<Item> itemLijst : itemMap.values()) {
			for (Item item : itemLijst) {
				output += item + System.lineSeparator();
			}
		}
		
		return output;
	}
	
	/**
	 * Zoeken op basis van de gegeven string en eventueel gelimiteerd tot de gegeven type
	 * 
	 * @param zoekwoord
	 * @param type
	 * @return
	 */
	public static List<Item> zoekItemsOpDeelString(String zoekwoord, ItemTypes type)
	{
		return items.stream()
						.filter(i -> i.getTitel().toLowerCase().contains(zoekwoord.toLowerCase()) 
						&& (type == null ? true : i.getClass().getSimpleName().toLowerCase().equals(type.toString().toLowerCase())))
						.collect(Collectors.toList());
	}
	
	/**
	 * Haal de item uit de lijst obv index nummer
	 * 
	 * @param idx
	 * @return
	 */
	public static Item getItemObvIdx(int idx)
	{
		return items.get(idx);
	}
	
	/**
	 * Haal de Item op obv id, null als niet bestaande
	 * 
	 * @param id
	 * @return Item|null
	 */
	public static Item getItemObvID(UUID id)
	{
		return items.stream().filter(i -> i.getID() == id).findFirst().get();
	}
	
	/**
	 * Voeg eentje toe aan de lijst
	 * 
	 * @param item
	 */
	public static void itemToevoegen(Item item)
	{
		items.add(item);
	}
}
