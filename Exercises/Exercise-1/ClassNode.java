// Collin Hargreaves
// CIS 22C
// Professor Mirsaeid Abolghasemi
// 04.21.2023

private class Node 
{

	private T data; // entry to list 
	private Node next; // link to next node
	
	// constructor
	private Node(T dataPortion)
	{
		this(dataPortion, null);
	} 

	// alternate constructor
	private Node(T dataPortion, Node nextNode) 
	{
		data = dataPortion;
		next = nextNode;
	} 

	// data getter
	private T getData() 
	{
		return data;
	}

	// data setter
	private void setData(T newData) 
	{
		data = newData;
	}

	// node getter
	private Node getNextNode() 
	{
		return next;
	}

	// node setter
	private void setNextNode(Node nextNode) 
	{
		next = nextNode;
	}
	
}
	
}

