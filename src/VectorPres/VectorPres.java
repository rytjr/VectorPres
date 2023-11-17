package VectorPres;

import java.util.*;
import java.io.*;

/*수직선 위에 N개의 좌표 X1, X2, ..., XN이 있다. 이 좌표에 좌표 압축을 적용하려고 한다.

Xi를 좌표 압축한 결과 X'i의 값은 Xi > Xj를 만족하는 서로 다른 좌표 Xj의 개수와 같아야 한다.

X1, X2, ..., XN에 좌표 압축을 적용한 결과 X'1, X'2, ..., X'N를 출력해보자.*/

public class VectorPres {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int Num = Integer.parseInt(bf.readLine());
		
		// 원본 배열과 비교하고 결과를 저장하기 위해 3개의 배열을 생성
		int[] arr = new int[Num];
		int[] arr2 = new int[Num];
		int[] arr3 = new int[Num];
		
		st = new StringTokenizer(bf.readLine());
		for(int i = 0; i < Num; i++) {
			
			arr[i] = Integer.parseInt(st.nextToken());
			arr2[i] = arr[i];
		}
		
		mergeSort(arr, 0, Num - 1);
		
		//arr배열을 순서를 유지하며 중복을 제거하는 코드
		arr = Arrays.stream(arr).distinct().toArray();
		
		//복사 배열의 값이 정렬한 배열의 몇번째인지 확인하고 결과를 arr3배열에 저장 방법은 이분탐색을 사용함
		for(int i = 0; i < Num; i++) {
			arr3[i] = TwoSearch(arr2[i], arr);
		}
		
		/*
		아래와 같은 2중 for 문을 사용하면 시간초과가 생길수밖에 없음 버블정렬과 비슷하다고 생각함
		때문에 큰 배열에서 값을 찾아야 할 경우 검색알고리즘을 사용해야함
		이번에는 이분검색 알고리즘을 사용할 예정임
		이분검색 알고리즘은 정렬된 배열에 사용할 수 있음
		
		for(int i = 0; i < arr2.length; i++) {
			for(int j = 0 ;j < arr.length; j++) {
				if(arr[j] == arr2[i]) {
					arr3[i] = j;
				}
			}
		}
		*/
		
		for(int i : arr3) {
			bw.write(i + " ");
		}
		bw.flush();
		bw.close();
		
		
		
 	}
	
	//이분탐색을 이용해서 배열에서 원하는 값을 검색
	public static int TwoSearch(int key, int[] arr) {
		int left = 0;
		int right = arr.length - 1;
		
		while(left <= right) {
			int middle = left + (right - left) / 2;
			
			if(arr[middle] == key) {
				return middle;
				
			}
			else if(arr[middle] > key) {
				right = middle - 1;
			}
			else {
				left = middle + 1;
			}
		}
		return - 1;
	}
	
	
	public static void mergeSort(int[] arr, int left, int right) {
		if(left < right) {
			
			int middle = (left + right) / 2;
			
			mergeSort(arr, left, middle);
			mergeSort(arr, middle + 1, right);
			
			merge(arr, left, middle, right);
		}
	}
	
	public static void merge(int[] arr, int left, int middle, int right) {
		
		int n1 = middle - left + 1;
		int n2 = right - middle;
		
		int[] arrleft = new int[n1];
		int[] arrright = new int[n2];
		
		for(int i = 0; i < n1; i++) {
			arrleft[i] = arr[i + left];
		}
		for(int i = 0; i < n2; i++) {
			arrright[i] = arr[i + middle + 1];
		}
		
		int i = 0; int j =0; int k = left;
		
		while(i < n1 && j < n2) {
			if(arrleft[i] <= arrright[j]) {
				arr[k] = arrleft[i];
				i++;
			}
			else {
				arr[k] = arrright[j];
				j++;
			}
			k++;
		}
		
		while(i < n1) {
			arr[k] = arrleft[i];
			i++;
			k++;
		}
		while(j < n2) {
			arr[k] = arrright[j];
			j++;
			k++;
		}
		
	}

}
