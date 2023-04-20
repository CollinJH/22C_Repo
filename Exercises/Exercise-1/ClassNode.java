public class ClassNode {

	private T data; // entry to list 
	private Node next; // link to next node
	
	private Node(T dataportion)
	{
		this(dataportion, null);
	} // ends constructor
	
	private Node(T dataportion, Node nextNode) {
		data = dataPortion;
		next = nextNode;
	}
}

