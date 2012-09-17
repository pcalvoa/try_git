


public class Hello {
	
	public Hello(){
		
	}
	
	public String writeHelloWorld(){
		return "hello world!!!";
	}
	
	static public void main(String[] argv){
		
		Hello h = new Hello();
		
		System.out.println(h.writeHelloWorld());
				
		
	}
	
}