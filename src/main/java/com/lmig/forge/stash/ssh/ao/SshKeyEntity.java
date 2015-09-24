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

package com.lmig.forge.stash.ssh.ao;

import java.util.Date;

import net.java.ao.Accessor;
import net.java.ao.Entity;
import net.java.ao.Mutator;
import net.java.ao.schema.NotNull;
import net.java.ao.schema.StringLength;
import net.java.ao.schema.Table;

@Table("ENTKEY")
public interface SshKeyEntity extends Entity{
    
    @Mutator("KEYID")
    Integer getKeyId();
    @NotNull
    @Mutator("TEXT")
    @StringLength(value=767)
    String getText();
    @NotNull
    @Mutator("LABEL")
    String getLabel();
    @NotNull
    @Mutator("USERID")
    Integer getUserId();
    @NotNull
    @Mutator("CREATED")
    Date getCreatedDate();
    

    @Accessor("KEYID")
    void setKeyId(int keyId);
    @Accessor("TEXT")
    void setText(String text);
    @Accessor("LABEL")
    void setLabel(String label);
    @Accessor("USERID")
    void setUserId(Integer id);
    @Accessor("CREATED")
    void setCreatedDate(Date created);
    
}
