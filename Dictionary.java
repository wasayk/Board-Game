/*
 * This class represents the Dictionary. Dictionary implements DictionaryADT
 * @author Abdul-Wasay Khan.
 */

public class Dictionary implements DictionaryADT {
	
	
	/*
	 * This class represents the HashNode LinkedList. Each HashNode has a key, score and level
	 * @author Abdul-Wasay Khan.
	 */
	private class HashNode {
		private String key;
		private int score;
		private int level;
		// variable next of type HashNode to traverse through the linkedList
		private HashNode next;
		// object rec of type Record to work with in our methods
		private Record rec = new Record(key, score, level); 
		
		   /*
		    * Constructor creates a HashNode with the parameters key, score, level
		    * @param key key of the HashNode
		    * @param score score of the HashNode
		    * @param level level of the HashNode
		    */
		private HashNode (String key, int score, int level) {
			this.key = key;
			this.score = score;
			this.level = level;
		}

		   /*
		    * Get the key of HashNode
		    * @return key of the HashNode
		    */
		public String getKey() {
			return this.key;
		}
		
		//No need for getters for score and level of HashNode because we only want to compare keys
	}
	
	
	   /*
	    * This is the initial class so we will be
	    * initializing the member variables here
	    */	
	private HashNode table[]; 
	private static final int m = 9887;
	//numRecords to keep track of Records
	private int numRecords;
	private static final int p = 31;
	
	   /*
	    * Constructor of Dictionary creates a table with the parameter size
	    * @param size size of the table
	    */
	public Dictionary(int size) {
		table = new HashNode[size];
	}
	
	
	   /*
	    * Get the table index of our Node
	    * @param key key of the HashNode
	    * @return table index of our node
	    */
	private int getTableIndex(String key) {
		// creating variable hash which will be the table index
        int hash = 0;
        //looping through the parameter's length
        for (int i = 0; i < key.length(); i++) {
        	// getting the hashCode of our key
            hash = (((p ^ i)*hash + (int)key.charAt(i)) % m);
        }
        
        //returning the table index by modding our hashcode by m
        return hash%m;
   
    }
		
	
	   /*
	    * Get the table index of our Node
	    * @param Record rec, putting this parameter into our table
	    * @return 1, 0, or exception based on our results
	    */
	public int put(Record rec) throws DuplicatedKeyException {
		
		//getting hashCode of our parameter and storing into local variable table index
		int tableIndex = getTableIndex(rec.getKey());
		//creating a HashNode called head that is pointing to our table at the index of our parameter
		HashNode head = table[tableIndex];
		
		//checking to see if the node we created is null
		if (head == null) {
			//if it is then we place the new Record of our parameter at the index
			table[tableIndex] = new HashNode(rec.getKey(), rec.getScore(), rec.getLevel());
			// we increase the number of records
			numRecords++;
			// we return 0 to confirm that the previous steps have been fulfilled
			return 0;
		
			
		// if head is not null	
		} else {
			//we loop through the linkedList while it is not null
			while(head != null) {
				// if the key of the nodes match with parameter's key
				if (head.getKey() == (rec.getKey())) {
					// we throw an exception to state that the key exists
					throw new DuplicatedKeyException("Key exists");
				}
				
				// we get the next node in the linkedList
				head = head.next;
			}
			
			// if we have looped and not found a duplicate key
			
			// we assign head to the table at the index
			head = table[tableIndex];
			// we create a newNode that also points to the index
			HashNode newNode = table[tableIndex];
			// we create an new Record that is assigned to the newNode
			newNode = new HashNode(rec.getKey(), rec.getScore(), rec.getLevel());
			// we make the newNode's next be head  (link them)
			newNode.next = head;
			//we make the newNode point to the initial index
			table[tableIndex] = newNode;
			//we have added the newNode to the linkedList so we increase the numRecords
			numRecords++;
			// we return 1 because we added a Node but there was a collision
			return 1;
		}
	}


	   /*
	    * remove the desired node through its key
	    * @param key key of our node
	    * @return void or throw exception based on results
	    */
	@Override
	public void remove(String key) throws InexistentKeyException {
		
		//getting hashCode of our parameter and storing into local variable table index
		int tableIndex = getTableIndex(key);
		//creating a HashNode called node that is pointing to our table at the index of our parameter
		HashNode node = table[tableIndex];
		
		//checking to see if the node we created is null
		if (node == null) {
			// if it is then we throw an exception that key does not exist
			throw new InexistentKeyException("Key does not exist");
		}
			
		//if the node's key is the key we are trying to remove
		if (node.getKey() == key) {
			// we point the table at the index to node's next
			table[tableIndex] = node.next;
			// and assign that to null
			node.next = null;
			// we have removed the key by breaking the link so we decrement our numRecords
			numRecords--;
		}
		
		
		//we then make a HashNode called prev that is assigned to node
		HashNode prev = node;
		//we get node's next
		node = node.next;
		
		
		// we traverse through the list while node is not null
		while (node != null) {
			// if our node matches the desired key
			if(node.getKey() == key){
				// we remove it by breaking the links
				prev.next = node.next;
				node.next = null;
				// we decrement numRecords as our node has been removed
				numRecords--;
			}
			// if it does not then we continue to the next while keeping the link
			prev = node;
			node = node.next;
		}
	}
		// TODO Auto-generated method stub
		

	   /*
	    * get the desired node through its key
	    * @param key key of our node
	    * @return the record that matches our key
	    */
	@Override
	public Record get(String key) {
		//getting hashCode of our parameter and storing into local variable table index
		int tableIndex = getTableIndex(key);
		//creating a HashNode called node that is pointing to our table at the index of our parameter
		HashNode node = table[tableIndex];
		
		// if the node we created is null
		if (node == null) {
			// we return null
			return null;
		}
		
		// we traverse through our list while node is not null
		while  (node != null) {
			// if the node's key matches
			if (node.getKey().equals(key)) {
				//we return the record of that key
				return node.rec;
			}
			// else we go to the next node
			node = node.next;
		}
		// if still not found then we return null
		return null;
	}

	   /*
	    * get number of records in our table
	    * @return the number of records in our table
	    */
	@Override
	public int numRecords() {
		return numRecords;
	}
	
	
}

