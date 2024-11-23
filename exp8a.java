import java.util.*;
import java.util.logging.*;
class Patient{
    String name,id,addr,disease,gender,dob;int age;long phno;
    Patient(String a,String b,String c,String d,String e,String f,long g,int h){
        name=a;
        id=b;addr=c;disease=d;gender=e;dob=f;phno=g;age=h;
    }

    String getname(){
        return name;
    }
    String getid(){
        return id;
    }
    int getage(){
        return age;
    }
    String getaddr(){
        return addr;
    }
    String getdis(){
        return disease;
    }
    String getgen(){
        return gender;
    }
    String getdob(){
        return dob;
    }
    long getphno(){
        return phno;
    }

    public void describe(){
        System.out.println("Name: "+name+" ID: "+id+" address: "+addr+" Disease: "+disease+" DOB: "+dob+" phno: "+phno);
    }
    public String describe1(){
        return "Name: "+name+" ID: "+id+" address: "+addr+" Disease: "+disease+" DOB: "+dob+" phno: "+phno;
    }
}




public class exp8a {
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        Patient ob1=new Patient("cde","123","madurai","fever","male","13/11/24",1234567890,25);
        Patient ob2=new Patient("ade","123","madurai","fever","male","13/11/24",1234567890,25);
        Patient ob3=new Patient("bde","123","madurai","fever","male","13/11/24",1234567890,25);
        ArrayList<Patient> list=new ArrayList<Patient>();
        list.add(ob1);
        list.add(ob2);
        list.add(ob3);
        System.out.println(list);
        Iterator<Patient> it=list.iterator();
        try{
            LogManager lm=LogManager.getLogManager();
            Logger lg=Logger.getLogger("Sample");
            FileHandler fh=new FileHandler("exp8a",true);
            lm.addLogger(lg);
            lg.addHandler(fh);
            fh.setFormatter(new SimpleFormatter());
            lg.log(Level.INFO,ob1.describe1());
        }
        catch(Exception e){
            System.out.println("Exception: "+e);
        }
        /* 
        System.out.print("enter gender: ");
        String a=sc.next();
        while(it.hasNext()){
            Patient p=it.next();
            if(p.getgen().equals(a)){
                p.describe();
            }
        }*/
        
        it=list.iterator();
        System.out.print("enter age: ");
        int b=sc.nextInt();
        while(it.hasNext()){
            Patient p=it.next();
            if(p.getage()>b){
                p.describe();
            }
        }
/* 
        it=list.iterator();
        System.out.print("enter id: ");
        a=sc.next();
        while(it.hasNext()){
            Patient p=it.next();
            if(p.getid().equals(a)){
                p.describe();
            }
        }
        Collections.sort(list, new Comparator<Patient>(){
            public int compare(Patient p1,Patient p2){
                return p1.name.compareTo(p2.name);
            }
        });
        //System.out.println("Sorted: "+list);
        Iterator<Patient> it=list.iterator();
        while(it.hasNext()){
            Patient p=it.next();
            p.describe();
        }*/

    }
    
}
