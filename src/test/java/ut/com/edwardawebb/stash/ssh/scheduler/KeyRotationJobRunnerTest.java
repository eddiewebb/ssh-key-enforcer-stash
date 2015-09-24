/*
 * Copyright 2015, Liberty Mutual Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ut.com.edwardawebb.stash.ssh.scheduler;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import net.java.ao.test.jdbc.NonTransactional;

import org.junit.Before;
import org.junit.Test;

import com.lmig.forge.stash.ssh.keys.EnterpriseSshKeyServiceImpl;
import com.lmig.forge.stash.ssh.scheduler.KeyRotationJobRunner;




public class KeyRotationJobRunnerTest {
   
    
    @Before
    public void setup(){
    }
    
    @Test
    public void enterpriseServiceExpireFunctionIsCalledOnExecution(){
        EnterpriseSshKeyServiceImpl service = mock(EnterpriseSshKeyServiceImpl.class);

        KeyRotationJobRunner jobRunner = new KeyRotationJobRunner(service);
        jobRunner.runJob(null);
        
        verify(service).replaceExpiredKeysAndNotifyUsers();
        
    }
    
    
    
}
