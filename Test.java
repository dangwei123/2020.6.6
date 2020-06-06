小青蛙有一天不小心落入了一个地下迷宫,小青蛙希望用自己仅剩的体力值P跳出这个地下迷宫。为了让问题简单,假设这是一个n*m的格子迷宫,
迷宫每个位置为0或者1,0代表这个位置有障碍物,小青蛙达到不了这个位置;1代表小青蛙可以达到的位置。小青蛙初始在(0,0)位置,地下迷宫的出
口在(0,m-1)(保证这两个位置都是1,并且保证一定有起点到终点可达的路径),小青蛙在迷宫中水平移动一个单位距离需要消耗1点体力值,向上爬一个
单位距离需要消耗3个单位的体力值,向下移动不消耗体力值,当小青蛙的体力值等于0的时候还没有到达出口,小青蛙将无法逃离迷宫。现在需要
你帮助小青蛙计算出能否用仅剩的体力值跳出迷宫(即达到(0,m-1)位置)
import java.util.*;
public class Main{
    private static String res;
    private static int energy;
    private static boolean isFind;
    private static void back(int n,int m,int x,int y,List<String> list,int p,int[][] v){
        if(x<0||y<0||x>=n||y>=m||p<0||v[x][y]==0) return;
        list.add("["+x+","+y+"]");
        v[x][y]=0;
        if(x==0&&y==m-1){
            isFind=true;
            if(p>=energy){
                energy=p;
                update(list);
            }
            list.remove(list.size()-1);
            v[x][y]=1;
            return;
        }
        
        back(n,m,x,y+1,list,p-1,v);
        back(n,m,x,y-1,list,p-1,v);
        back(n,m,x+1,y,list,p-3,v);
        back(n,m,x-1,y,list,p,v);
        list.remove(list.size()-1);
        v[x][y]=1;
    }
    private static void update(List<String> list){
        StringBuilder s=new StringBuilder();
        for(String str:list){
            s.append(str).append(",");
        }
        res=s.substring(0,s.length()-1);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int n=sc.nextInt();
            int m=sc.nextInt();
            int p=sc.nextInt();
            int[][] arr=new int[n][m];
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    arr[i][j]=sc.nextInt();
                }
            }
            
            back(n,m,0,0,new ArrayList<>(),p,arr);
            
            if(isFind){
                System.out.println(res);
            }else{
                System.out.println("Can not escape!");
            }
            
        }
    }
}


给定一个十进制数M，以及需要转换的进制数N。将十进制数M转化为N进制数
import java.util.Scanner;
public class Main{
    private static char[] chars={'0','1','2','3','4','5','6','7','8','9','A','B',
                                  'C','D','E','F'};
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int M=sc.nextInt();
            int m=sc.nextInt();
            StringBuilder sb=new StringBuilder();
            int n=Math.abs(M);
            while(n!=0){
                int index=n%m;
                sb.append(chars[index]);
                n/=m;
            }
            if(M>=0)
            System.out.println(sb.reverse());
            else System.out.println("-"+sb.reverse());
        }
    }
}


给定一个有n个正整数的数组A和一个整数sum,求选择数组A中部分数字和为sum的方案数。
当两种选取方案有一个数字的下标不一样,我们就认为是不同的组成方案。
import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int n=sc.nextInt();
            int sum=sc.nextInt();
            int[] arr=new int[n];
            for(int i=0;i<n;i++){
                arr[i]=sc.nextInt();
            }
           
            long[] dp=new long[sum+1];
            dp[0]=1;
            for(int i=0;i<n;i++){
                for(int j=sum;j>=arr[i];j--){
                    dp[j]+=dp[j-arr[i]];
                }
            }
            System.out.println(dp[sum]);
        }
    }
}

给定无序整数序列，求其中第K大的数，例如{45，67，33，21}，第2大数为45
import java.util.Scanner;
public class Main{
    private static int res;
    private static void quickSort(int[] arr,int left,int right,int k){
        //if(left>=right) return;
        int pivot=partition(arr,left,right);
        if(pivot==k){
            res=arr[pivot];
            return;
        }else if(pivot<k){
            quickSort(arr,pivot+1,right,k);
        }else{
            quickSort(arr,left,pivot-1,k);
        }
    }
    private static int partition(int[] arr,int left,int right){
        int pivot=arr[left];
        int i=left;
        int j=right;
        while(i<j){
            while(i<j&&arr[j]>=pivot){
               j--;
            }
            arr[i]=arr[j];
            while(i<j&&arr[i]<=pivot){
                i++;
            }
            arr[j]=arr[i];
        }
        arr[i]=pivot;
        return i;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            String[] str=sc.nextLine().split(" ");
            int k=sc.nextInt();
            int[] arr=new int[str.length];
            for(int i=0;i<arr.length;i++){
                arr[i]=Integer.parseInt(str[i]);
            }
            
            quickSort(arr,0,arr.length-1,arr.length-k);
            System.out.println(res);
        }
    }
}

