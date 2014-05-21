package huffmanTrees;

/**
 * HuffmanTree class This class contains a method for building the tree, as well
 * as methods for adding nodes and for printing the tree
 * 
 * @author Eric
 * 
 */
public class HuffmanTree {

	private TreeNode HuffmanTree;

	public static void main(String[] args) {
		HuffmanTree hTree = new HuffmanTree("*e**a*dc*mr");
		hTree.print();
		String codedMessage = "10111001100111100";
		decompress(codedMessage, hTree);
	}

	// Constructor for objects of type HuffmanTree
	public HuffmanTree(String treeString) {
		if (treeString != null && treeString.length() != 0) {
			HuffmanTree = new TreeNode(treeString.charAt(0), null, null, null,
					"");
		}

		// Creates the root node
		TreeNode parent = HuffmanTree;

		// Builds the tree
		for (int k = 1; k < treeString.length(); k++) {
			parent = addNode(parent, treeString.charAt(k));
		}
	}

	// Adds nodes to the tree
	private TreeNode addNode(TreeNode current, Character value) {
		// Finds where the next node to be added to the tree should go
		while (current.isCharacter() || current.getRight() != null) {
			if (current.getParent() == null)
				throw new IllegalArgumentException(
						"A tree cannot be constructed from this String");
			else
				current = current.getParent();
		}

		// Adds the node as either a left or right subchild
		// If the right child is empty and the current node is a '*', add the
		// new node to the left child
		if (current.getLeft() == null) {
			TreeNode newNode = new TreeNode(value, null, null, current,
					current.getPath() + "0");
			current.setLeft(newNode);
			return newNode;
		}

		// If not, add it as the right child
		TreeNode newNode = new TreeNode(value, null, null, current,
				current.getPath() + "1");
		current.setRight(newNode);
		return newNode;
	}

	// Output
	public void print() {
		System.out.println("character\tbits\tencoding");
		System.out.println("--------------------------------");
		print(HuffmanTree);
	}

	// Decodes the bit string message and outputs it in characters
	public static void decompress(String message, HuffmanTree tree) {
		TreeNode root = tree.getRoot();
		TreeNode node = root;

		String decodedMessage = "";
		float numBits = 0;
		float numChars = 0;
		float compressionRatio;

		for (int k = 0; k < message.length(); k++) {

			// Traverse the tree to find out which bits map to which letter
			if (message.charAt(k) == '0') {
				node = node.getLeft();
			} else if (message.charAt(k) == '1') {
				node = node.getRight();
			}

			// If the node reached was a character, add all of the node's
			// relevant data to the final message
			if (node.isCharacter()) {
				numBits += node.getPath().length();
				decodedMessage += node.getValue();
				numChars++;
				node = root;
			}
		}

		// Outputs all the relevant data (The decoded message, the number of
		// bits required to write the message, the number of characters in the
		// message, and the compression ration of using a Huffman Tree
		System.out.println();
		System.out.println("Message: " + decodedMessage);
		System.out.println("Number of Bits: " + (int) numBits);
		System.out.println("Number of Characters: " + (int) numChars);
		compressionRatio = (numBits / (numChars * 8));
		System.out.println("Compression Ratio: " + (compressionRatio * 100)
				+ "%");
	}

	// Helper method for output
	// Gets each character in the tree, the amount of bits in its code sequence,
	// and its encoded sequence
	private void print(TreeNode node) {
		if (node.isCharacter()) {
			// Prints the relevant data for each node
			String path = node.getPath();
			System.out.println("   " + node.getValue() + "\t\t "
					+ path.length() + "\t" + path);
		}

		// Traverses the left subtree
		if (node.getLeft() != null) {
			print(node.getLeft());
		}

		// Traverses the right subtree
		if (node.getRight() != null) {
			print(node.getRight());
		}
	}

	// Returns the root node of the tree
	public TreeNode getRoot() {
		return HuffmanTree;
	}

	/**
	 * TreeNode class Each node stores a string containing its encoding
	 * sequence, its character value, its left and right child nodes, and its
	 * parent node
	 * 
	 * @author Eric
	 * 
	 */
	public class TreeNode {
		private final Character value;
		private TreeNode left;
		private TreeNode right;
		private final TreeNode parent;
		private final String path;

		// Constructor for objects of type TreeNode
		// Stores each node's character value, left and right subtrees, parent,
		// and its encoding path
		public TreeNode(Character startValue, TreeNode startLeft,
				TreeNode startRight, TreeNode startParent, String startPath) {
			value = startValue;
			left = startLeft;
			right = startRight;
			parent = startParent;
			path = startPath;

		}

		// Gets the node's code path
		public String getPath() {
			return path;
		}

		// Returns whether or not the node is a character
		public boolean isCharacter() {
			return !value.equals('*');
		}

		// Gets the node's left child
		public TreeNode getLeft() {
			return left;
		}

		// Gets the node's right child
		public TreeNode getRight() {
			return right;
		}

		// Gets the node's parent
		public TreeNode getParent() {
			return parent;
		}

		// Returns the type of node, a '*' or a character
		public Character getValue() {
			return value;
		}

		// Sets the node's left child
		public void setLeft(TreeNode left) {
			this.left = left;
		}

		// Sets the node's right child
		public void setRight(TreeNode right) {
			this.right = right;
		}
	}
}