package qtx.spring.flowable.common;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author qtx
 * @since 2024/2/27
 */
@Component
public class FlowableFactory {

    @Autowired
    private ProcessEngine processEngine;


    public RepositoryService getRepositoryService() {
        return processEngine.getRepositoryService();
    }

    public RuntimeService getRuntimeService() {
        return processEngine.getRuntimeService();
    }

    public TaskService getTaskService(){
        return processEngine.getTaskService();
    }
}
