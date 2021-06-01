public class SlangWord {
	private String word;
	private String definition;
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
	public SlangWord(String temp) {
		int flag=0;
		this.word=new String();
		this.definition=new String();
		if(temp.charAt(temp.length()-1)=='`')
		{
			for(int i=0;i<temp.length();i++)
			{
				this.word+=temp.charAt(i);
			}
			this.definition="0";
		}
		if(temp.contains("`"))
		{
			for (int i=0;i<temp.length();i++)
			{
				if(temp.charAt(i)=='`')
				{
					flag++;
					continue;
				}
				if(flag==0)
				{
					this.word+=temp.charAt(i);
				}
				else
				{
					this.definition+=temp.charAt(i);
				}
			}
		}
		else
		{
			for(int i=0;i<temp.length();i++)
			{
				this.definition+=temp.charAt(i);
			}
			this.word="";
		}
	}
	
	public String convertWordToString(SlangWord temp) {
		return temp.word+"`"+temp.definition+"\n";
	}
	public void print() {
		System.out.println(this.word+": "+this.definition);
	}
}
