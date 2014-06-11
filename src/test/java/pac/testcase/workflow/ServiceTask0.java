package pac.testcase.workflow;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class ServiceTask0 implements JavaDelegate{

	public void execute(DelegateExecution execution) throws Exception {
		execution.getVariables().get("");
	}

}
