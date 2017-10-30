package  com.lokia.pm.main;

import java.util.Queue;
import java.util.concurrent.SynchronousQueue;

import com.lokia.pm.service.PmService;

/**
 * @author gushu
 * @date 2017/10/30
 */
public class PMTest {

	public static void main(String[] args) {
		int threadNum = Runtime.getRuntime().availableProcessors();
		System.err.println("threadNum:"+threadNum);
		Queue<String> queue = getBlockingQueue();
		PmService pmService = new PmService();
		pmService.setQueue(queue);
		pmService.test(threadNum);
	}


	private static Queue<String> getBlockingQueue() {
//		return new LinkedBlockingDeque<>();
//		return new LinkedBlockingQueue<>();
		return new SynchronousQueue<>();
	}

}
