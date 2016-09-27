
public class MazeObject {	
	String type;
	
	public MazeObject(String type)
	{
		this.type = type;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	@Override
	public boolean equals(Object other)
	{
		if (other instanceof MazeObject)
		{
			MazeObject obj = (MazeObject) other;
			if (this.type.equals(obj.type))
				return true;
			else
				return false;
		}
		else
			return false;		
	}
	
	public int compareTo(Object other)
	{
		if (other instanceof MazeObject)
		{
			MazeObject obj = (MazeObject) other;
			if (this.type.equals(obj.type))
				return 0;
			else
				return 1;
		}
		else
			return 1;		
	}
	
	@Override
	public String toString()
	{
		return String.valueOf(this.type);
	}

}

