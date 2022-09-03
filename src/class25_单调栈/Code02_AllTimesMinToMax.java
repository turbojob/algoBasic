package class25_单调栈;

import java.util.Stack;

public class Code02_AllTimesMinToMax {

	public static int max1(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				int minNum = Integer.MAX_VALUE;
				int sum = 0;
				for (int k = i; k <= j; k++) {
					sum += arr[k];
					minNum = Math.min(minNum, arr[k]);
				}
				max = Math.max(max, minNum * sum);
			}
		}
		return max;
	}

	public static int max21(int[] arr) {
		int size = arr.length;
		int[] sums = new int[size];
		sums[0] = arr[0];
		for (int i = 1; i < size; i++) {
			sums[i] = sums[i - 1] + arr[i];
		}
		int max = Integer.MIN_VALUE;
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < size; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
				int j = stack.pop();
				max = Math.max(max, (stack.isEmpty() ? sums[i - 1] : (sums[i - 1] - sums[stack.peek()])) * arr[j]);
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			int j = stack.pop();
			max = Math.max(max, (stack.isEmpty() ? sums[size - 1] : (sums[size - 1] - sums[stack.peek()])) * arr[j]);
		}
		return max;
	}
	public static int max2(int[] arr){
		if(arr == null || arr.length == 0)
			return -1;
		int ret = Integer.MIN_VALUE;
		int N = arr.length;
		Stack<Integer> stack = new Stack<>();
		int[][] ans = new int[N][2];
		for(int i=0; i<N; i++){
			//每一次认为 自己是 min   不断扩充L, R
			while(!stack.isEmpty() && arr[stack.peek()] >= arr[i] ){
				Integer pop = stack.pop();
				int left = stack.isEmpty() ? -1 : stack.peek();
				ans[pop][0] = left;
				ans[pop][1] = i;
			}
			stack.add(i);
		}
		while (!stack.isEmpty()){
			Integer pop = stack.pop();
			int left = stack.isEmpty() ? -1 : stack.peek();
			ans[pop][0] = left;
			ans[pop][1] = -1;
		}
		//产生前缀和数组
		int[] sum = new int[N];
		sum[0] = arr[0];
		for(int i=1; i<N; i++){
			sum[i] = sum[i-1] + arr[i];
		}
		//得到  每一个数  最近的比他小的位置  -1代表没有  越界
		for(int i=0; i<N; i++){
			int l = ans[i][0];
			int r = ans[i][1];
			//计算累加和
			int add = 0;
			if(l == -1 && r == -1)
				add = sum[N-1];
			else if(l == -1)
				add = sum[r-1];
			else if(r == -1)
				add = sum[N-1]-sum[l];
			else
				add = sum[r-1] - sum[l];
			ret = Math.max(ret, add * arr[i]);
		}
		return ret;
	}

	public static int[] gerenareRondomArray() {
		int[] arr = new int[(int) (Math.random() * 20) + 10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 101);
		}
		return arr;
	}

	public static void main(String[] args) {
		int testTimes = 2000000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			int[] arr = gerenareRondomArray();
			if (max1(arr) != max2(arr)) {
				System.out.println("FUCK!, and arr = ");
				for(int j: arr){
					System.out.printf(j+" ");
				}
				System.out.println("m1 = "+max1(arr) +", m2 = "+max2(arr));
				break;
			}
		}
		System.out.println("test finish");
	}

}
