import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[]args) throws IOException
	{
		Scanner si=new Scanner(System.in);
		Scanner st=new Scanner(System.in);
		WordsMap data=new WordsMap("input.txt");
		while(true)
		{
			int menu;
			System.out.println("Nhap 1 de tim kiem theo SlangWord");
			System.out.println("Nhap 2 de tim kiem theo dinh nghia");
			System.out.println("Nhap 3 de hien thi lich su tim kiem");
			System.out.println("Nhap 4 de them SlangWord moi");
			System.out.println("Nhap 5 de edit 1 SlangWord");
			System.out.println("Nhap 6 de xoa 1 SlangWord");
			System.out.println("Nhap 7 de reset ve danh sach SlangWord goc");
			System.out.println("Nhap 8 de random 1 SlangWord");
			System.out.println("Nhap 9 de choi tro do vui SlangWord");
			System.out.println("Nhap 10 de choi tro do vui dinh nghia");
			System.out.println("Nhap 0 de ket thuc chuong trinh");
			menu=si.nextInt();
			switch(menu)
			{
			case 1:
			{
				System.out.println("Nhap SlangWord can tim: ");
				String word=st.nextLine();
				SlangWord slw=data.find(word);
				if(slw!=null)
				{
					System.out.println("SlangWord can tim: ");
					slw.print();
				}
				break;
			}
			case 2:
			{
				System.out.println("Nhap dinh nghia can tim: ");
				String def=st.nextLine();
				data.findByDef(def);
				break;
			}
			case 3:
			{
				data.printSearchList();
				break;
			}
			case 4:
			{				
				SlangWord newSl=data.newSlang();
				System.out.println("SlangWord vua tao: ");
				newSl.print();
				break;
			}
			case 5:
			{
				System.out.println("Nhap SlangWord can edit: ");
				String slw=st.nextLine();
				data.edit(slw);
				break;
			}
			case 6:
			{
				System.out.println("Nhap SlangWord can xoa: ");
				String slw=st.nextLine();
				System.out.println("Ban co chac chan muon xoa?");
				System.out.println("Nhap 1 de xoa, nhap 0 de huy");
				int menu2 =si.nextInt();
				if(menu2==1)
				{
					data.delete(slw);
				}
				else
				{
					System.out.println("Da huy thao tac");
				}
				break;
			}
			case 7:
			{
				data.reset("input.txt");
				break;
			}
			case 8:
			{
				System.out.println("SlangWord duoc random: ");
				data.random();
				break;
			}
			case 9:
			{
				while(true)
				{
					data.guessBy("word");
					System.out.println("Ban co muon choi tiep? ");
					System.out.println("Nhap 1 de choi tiep, nhap 0 de dung lai");
					int choose=si.nextInt();
					if(choose==0)
					{
						break;
					}
				}
				break;
			}
			case 10:
			{
				while(true)
				{
					data.guessBy("def");
					System.out.println("Ban co muon choi tiep? ");
					System.out.println("Nhap 1 de choi tiep, nhap 0 de dung lai");
					int choose=si.nextInt();
					if(choose==0)
					{
						break;
					}
				}
				break;
			}
			case 0:
			{
				System.out.println("Ban co muon luu lai du lieu da thay doi?");
				System.out.println("Nhap 1 de luu");
				System.out.println("Nhap 0 de thoat");
				int menu2=si.nextInt();
				if(menu2==1)
				{
					data.save();
				}
				System.out.println("Chuong trinh ket thuc");
				System.exit(menu2);
			}
			}
			System.out.println("Nhap Enter de tiep tuc");
			try{
				System.in.read();
			}catch(Exception e) {};
		}
	}
}
