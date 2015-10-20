package testing;

public class File {
	private String content;
	
	public File(String content){
		this.content=content;
	}
@Override
public String toString(){
	return content;
}
}
