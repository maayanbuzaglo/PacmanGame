package Packman_Game;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class ShortestPathAlgo {

	Game g;
	private int numberOfPacman;
	private Stack<Integer> stack;

	public ShortestPathAlgo(Game g) {

		this.g = g;
		this.stack = new Stack<Integer>();
	}


	public  ShortestPathAlgo()
	{
		this.g = null;
		stack = new Stack<Integer>();
		
	}
	
    public void tsp()
    {
    	Path p = new Path();
    	double[][] arr = new double[g.getPacman_list().size()][g.getFruit_list().size()];
    	for (int i = 0; i < g.getPacman_list().size(); i++) {
    		for (int j = 0; j < g.getFruit_list().size(); j++) {
    			double dis = g.getPacman_list().get(i).getLocation().distance3D(g.getFruit_list().get(j).getLocation());
				arr[i][j] = dis / g.getPacman_list().get(i).getSpeed();
				System.out.print(arr[i][j] + " ");
			}
    		System.out.println("");
		}
        numberOfPacman = arr.length - 1;
        int[] eaten = new int[numberOfPacman + 1];
        eaten[1] = 1;
        stack.push(1);
        int element, dst = 0, i;
        int min = Integer.MAX_VALUE;
        boolean minFlag = false;
        System.out.print(1 + "\t");
 
        while (!stack.isEmpty())
        {
            element = stack.peek();
            i = 1;
            min = Integer.MAX_VALUE;
            while (i <= numberOfPacman)
            {
                if (arr[element][i] > 1 && eaten[i] == 0)
                {
                    if (min > arr[element][i])
                    {
                        min = (int) arr[element][i];
                        dst = i;
                        minFlag = true;
                    }
                }
                i++;
            }
            if (minFlag)
            {
                eaten[dst] = 1;
                stack.push(dst);
                System.out.print(dst + "\t");
                minFlag = false;
                continue;
            }
            stack.pop();
        }
    }
    public static void main(String[] args) {
		
////		Game g = new Game("C:\\Users\\מעיין\\Desktop\\data\\game_1543684662657.csv");
////		for (Pacman it1: g.Pacman_list) {
////			System.out.println(it1.toString());
////		}
////		for (Fruit it: g.Fruit_list) {
////			System.out.println(it.toString());
////		}
//		ShortestPathAlgo s = new ShortestPathAlgo(g);
//		s.tsp();
//		
	}
}
