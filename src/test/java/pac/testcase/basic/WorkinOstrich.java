package pac.testcase.basic;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.container.PreMatching;

public class WorkinOstrich {
	class Mark{
		int distance;
		int cabbageCount;
		
		public int getDistance() {
			return distance;
		}
		public void setDistance(int distance) {
			this.distance = distance;
		}
		public int getCabbageCount() {
			return cabbageCount;
		}
		public void setCabbageCount(int cabbageCount) {
			this.cabbageCount = cabbageCount;
		}
		public Mark(int distance, int cabbageCount) {
			super();
			this.distance = distance;
			this.cabbageCount = cabbageCount;
		}
	}
	
	int load = 1000;
	int missionCabbageCount = 3000;
	int missionDistance = 1000;
	Mark prevMark = new Mark(0, 3000);
	Mark currentMark = new Mark(0, 0);
	
	
	
	void go(){
		for (int i = 0; i < missionDistance;i++,missionCabbageCount--) {
			if(i==prevMark.getDistance()+333){
				i=prevMark.getDistance();
				missionCabbageCount-=333;
				currentMark.setDistance(prevMark.getDistance()+333);
				
				currentMark.cabbageCount+=334;
				prevMark.cabbageCount = prevMark.cabbageCount>1000?prevMark.cabbageCount-1000:0;
				if(prevMark.getCabbageCount()<=0)
					prevMark.setDistance(prevMark.getDistance()+333);
			}
		}
		int sum = 3000;
		int load_num = 1000;
		int result = 0;
		int time = sum / load_num-1;
		for (int i = time; i > 0 ;--i) {
		result += load_num / ((i*2) + 1);
		}
	}
	
	
	public static void main(String[] args) {
		WorkinOstrich os = new WorkinOstrich();
		os.go();
		System.out.println(os.missionCabbageCount);
		
		int sum = 3000;
		int load_num = 1000;
		int result = 0;
		int time = sum / load_num-1;
		for (int i = time; i > 0 ;--i) {
			result += load_num / ((i*2) + 1);
		}
		System.out.println(result);
	}
}
