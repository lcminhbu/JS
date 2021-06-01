import java.io.*;
import java.util.*;

public class WordsMap {
	private MyHash<String, String> hash;
	private ArrayList<String> searchList=new ArrayList<>();
	
	public WordsMap(String file) throws IOException {
		hash=new MyHash<>();
		searchList=new ArrayList<>();
		inputFromFile(file);
	}
	
	
	public void inputFromFile(String file) throws IOException {
		FileReader fin=null;
		BufferedReader bin=null;
		try {
			fin=new FileReader(file);
			bin=new BufferedReader(fin);
			String temp= bin.readLine();
			while(temp!=null)
			{
				SlangWord sword=new SlangWord(temp);
				this.hash.put(sword.getWord(), sword.getDefinition());
				temp=bin.readLine();
			}
		}catch (Exception e) {
		}finally {
			if(fin!=null)
			{
				fin.close();
			}
			if(bin!=null)
			{
				bin.close();
			}
		}
	}
	
	
	public SlangWord find(String slw) {
		Scanner sc=new Scanner(System.in);
		Date date=java.util.Calendar.getInstance().getTime();
		SlangWord result=null;
		if(hash.containsKey(slw))
		{
			result=new SlangWord(slw+"`"+hash.get(slw));
		}
		if(result==null)
		{
			System.out.println("SlangWord vua nhap khong ton tai, ban co muon them SlangWord moi?");
			System.out.println("Nhap 1 de them SlangWord moi");
			System.out.println("Nhap 0 de thoat");
			int menu2= sc.nextInt();
			if(menu2==1)
			{
				result = newSlang();
			}
		}
		searchList.add("Time: "+ date + ", Search key: "+ slw);
		return result;
	}
	
	
	public void findByDef(String def) {
		Scanner sc=new Scanner(System.in);
		Scanner s=new Scanner (System.in);
		Date date=java.util.Calendar.getInstance().getTime();
		Set<String> keySet=hash.keySet();
		int flag=0;
		for(String key:keySet)
		{
			if(hash.get(key).toUpperCase().contains(def.toUpperCase())==true)
			{
				flag++;
				if(flag==1)
				{
					System.out.println("Ket qua tim duoc: ");

				}
				System.out.println(flag+"/ " + key + ": " + hash.get(key));
			}
		}
		if(flag==0)
		{
			System.out.println("Dinh nghia vua nhap khong ton tai, ban co muon them SlangWord moi?");
			System.out.println("Nhap 1 de them SlangWord moi");
			System.out.println("Nhap 0 de thoat");
			int menu2= sc.nextInt();
			if(menu2==1)
			{
				newSlang();
			}
		}
		searchList.add("Time: "+ date + ", Search key: "+ def);
	}
	
	
	public void printSearchList() {
		for(int i=0;i<searchList.size();i++)
		{
			System.out.println(searchList.get(i));
		}
	}
	
	
	public SlangWord newSlang() {
		Scanner st=new Scanner(System.in);
		Scanner sc=new Scanner(System.in);
		System.out.println("Nhap SlangWord can them: ");
		String word=st.nextLine();
		System.out.println("Nhap dinh nghia cho SlangWord: ");
		String def=st.nextLine();
		if(hash.containsKey(word))
		{
			System.out.println("Slang word vua nhap da bi trung");
			System.out.println("Nhap 1 de overwrite len SlangWord cu");
			System.out.println("Nhap 2 de them dinh nghia cho SlangWord");
			System.out.println("Nhap 0 de dung them SlangWord");
			int menu2;
			menu2=sc.nextInt();
			switch(menu2)
			{
			case 1:
			{
				hash.replace(word,def);
				break;
			}
			case 2:
			{
				hash.replace(word, hash.get(word)+" | "+def);
				break;
			}
			case 0:
			{
				return null;
			}
			}
		}
		else
		{
			hash.put(word, def);
		}
		SlangWord add=new SlangWord(word+"`"+hash.get(word));
		return add;
	}
	
	
	public void edit(String slw) {
		Scanner sc=new Scanner(System.in);
		Scanner st=new Scanner(System.in);
		System.out.println("Nhap 1 de edit SlangWord: ");
		System.out.println("Nhap 2 de edit dinh nghia: ");
		System.out.println("Nhap 0 de dung edit: ");
		int menu2=sc.nextInt();
		switch(menu2)
		{
		case 1:
		{
			System.out.println("Hay nhap SlangWord moi: ");
			String newSlw=st.nextLine();
			hash.put(newSlw,hash.get(slw));
			hash.remove(slw);
			break;
		}
		case 2:
		{
			System.out.println("Hay nhap dinh nghia moi: ");
			String newDef=st.nextLine();
			hash.replace(slw,newDef);
			break;
		}
		case 0:
			break;
		}
	}
	
	
	public void delete(String slw) {
		if(hash.containsKey(slw))
		{
			hash.remove(slw);
		}
		else
		{
			System.out.println("SlangWord vua nhap khong ton tai!");
		}
	}
	
	
	public void reset(String file) throws IOException {
		hash.clear();
		inputFromFile(file);
	}
	
	
	public SlangWord random() {
		Random ran=new Random();
		int num=ran.nextInt(hash.size());
		Set<String> set=hash.keySet();
		System.out.println(num);
		int i=0;
		for(String k: set)
		{
			SlangWord sl= new SlangWord(k+"`"+hash.get(k));
			if(i==num)
			{
				return sl;
			}
			i++;
		}
		return null;
	}
	
	
	public void save() throws IOException {
		FileWriter fout=null;
		BufferedWriter bout=null;
		try {
			fout=new FileWriter("input.txt");
			bout=new BufferedWriter(fout);
			Set<String> key=hash.keySet();
			for(String k: key) {
				String s=k+"`"+hash.get(k)+"\n";
				bout.write(s);
				bout.flush();
			}
		}catch (Exception e) {
		}finally {
			if(fout!=null)
			{
				fout.close();
			}
			if(bout!=null)
			{
				bout.close();
			}
		}
	}
	
	
	public void guessBy(String choose) {
		Random ran=new Random();
		Set<String> set=hash.keySet();
		SlangWord[] sl=new SlangWord[4];
		HashSet<String> flag=new HashSet<>();
		for(int j=0;j<4;j++) {
			int num=ran.nextInt(hash.size());
			String temp=new String();
			temp+=(char)(num+'0');
			while(flag.contains(temp))
			{
				temp="";
				num=ran.nextInt(hash.size());
				temp+=(char)(num+'0');
			}
			flag.add(temp);
			int i=0;
			for(String k: set) {
				sl[j]= new SlangWord(k+"`"+hash.get(k));
				if(i==num)
				{
					break;
				}
				i++;
			}
			
		}
		int ans=ran.nextInt(4);
		String[]answer=new String[4];
		if(choose=="word")
		{
			System.out.println("SlangWord: "+sl[0].getWord());
			System.out.println("Dinh nghia: ");
			answer[ans]=sl[0].getDefinition();


		}
		else
		{
			System.out.println("Dinh nghia: "+sl[0].getDefinition());
			System.out.println("SlangWord: ");
			answer[ans]=sl[0].getWord();
		}
		String[] abcd= {"A/ ","B/ ","C/ ","D/ "};
		int k=0;
		for(int i=1;i<4;i++)
		{
			if(k==ans)
			{
				k++;
			}
			if(choose=="word")
			{
				answer[k]=sl[i].getDefinition();
			}
			else
			{
				answer[k]=sl[i].getWord();
			}
			k++;
		}
		for(int i=0;i<4;i++)
		{
			System.out.println(abcd[i]+answer[i]);
		}
		System.out.print("Hay nhap dap an: ");
		String dan;
		Scanner s=new Scanner(System.in);
		dan=s.nextLine();
		int dapAn;
		if((int) dan.charAt(0)>90)
		{
			dapAn=(int)dan.charAt(0)-97;
		}
		else
		{
			dapAn=(int)dan.charAt(0)-65;
		}
		if(dapAn== ans)
		{
			System.out.println("Ban da dung");
		}
		else
		{
			System.out.println("Bai da sai");
		}
	}
}
