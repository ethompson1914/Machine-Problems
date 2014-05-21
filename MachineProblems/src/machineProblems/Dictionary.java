package machineProblems;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {
	
	File dictionaryFile = null;
	RandomAccessFile dictionaryFileRA = null;
	
	public Dictionary(String dictionaryFilename)
	{
        try {
			dictionaryFile = new File(dictionaryFilename);
			dictionaryFileRA = new RandomAccessFile(dictionaryFile, "r");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	// lookup is implemented by binary search
	
	public List<String> lookup(String string) {
	    List<String> result = new ArrayList<String>();
	    try {
	        long low = 0;
	        long high = dictionaryFile.length();

	        long p = -1;
	        while (low < high) {
	            long mid = (low + high) / 2;
	            p = mid;
	            while (p >= 0) {
	                dictionaryFileRA.seek(p);

	                char c = (char) dictionaryFileRA.readByte();
	                //System.out.println(p + "\t" + c);
	                if (c == '\n')
	                    break;
	                p--;
	            }
	            if (p < 0)
	                dictionaryFileRA.seek(0);
	            String line = dictionaryFileRA.readLine();
	            //System.out.println("-- " + mid + " " + line);
	            if (line.compareTo(string) < 0)
	                low = mid + 1;
	            else
	                high = mid;
	        }

	        p = low;
	        while (p >= 0) {
	            dictionaryFileRA.seek(p);
	            if (((char) dictionaryFileRA.readByte()) == '\n')
	                break;
	            p--;
	        }

	        if (p < 0)
	            dictionaryFileRA.seek(0);

	        while (true) {
	            String line = dictionaryFileRA.readLine();
	            if (line == null || !line.equals(string))
	                break;
	            result.add(line);
	        }

	    } catch (IOException e) {
	        System.out.println("IOException:");
	        e.printStackTrace();
	    }
	    return result;
	}	
}