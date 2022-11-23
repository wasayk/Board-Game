
/*
 * This class represents the Record. Each Record  has a key, score and level.
 * @author Abdul-Wasay Khan.
 */

	   /*
	    * This is the initial class so we will be
	    * initializing the member variables here
	    */	
public class Record {
	private String key;
	private int score;
	private int level;
	

	   /*
	    * Constructor creates a Record with the parameters key, score, level
	    * @param key key of the record
	    * @param score score of the record
	    * @param level level of the record
	    */
	public Record (String key, int score, int level) {
		this.key = key;
		this.score = score;
		this.level = level;
	}
	

	   /*
	    * Get the key of Record
	    * @return key of the Record
	    */
	public String getKey() {
		return key;
	}
	

	   /*
	    * Get the score of Record
	    * @return score of the Record
	    */
	public int getScore() {
		return score;
	}
	

	   /*
	    * Get the level of Record
	    * @return level of the Record
	    */
	public int getLevel() {
		return level;
	}
	

}
