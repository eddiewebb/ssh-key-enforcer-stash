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

package com.lmig.forge.stash.ssh.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atlassian.annotations.PublicApi;
import com.atlassian.stash.user.StashAuthenticationContext;
import com.atlassian.stash.user.StashUser;
import com.lmig.forge.stash.ssh.EnterpriseKeyGenerationException;
import com.lmig.forge.stash.ssh.ao.SshKeyEntity;
import com.lmig.forge.stash.ssh.keys.EnterpriseSshKeyService;

/**
 * A resource of message.
 */
@Path("/keys")
@PublicApi
public class KeyDetailsResource {
    private final EnterpriseSshKeyService enterpriseKeyService;
    private final StashAuthenticationContext stashAuthenticationContext;

    public KeyDetailsResource(EnterpriseSshKeyService enterpriseKeyService,
            StashAuthenticationContext stashAuthenticationContext) {
        this.enterpriseKeyService = enterpriseKeyService;
        this.stashAuthenticationContext = stashAuthenticationContext;
    }


    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response generateNewPair() {

        StashUser user = stashAuthenticationContext.getCurrentUser();
        KeyPairResourceModel keyPair;
        try {
            keyPair = enterpriseKeyService.generateNewKeyPairFor(user);
        } catch (EnterpriseKeyGenerationException e) {
            return Response.serverError().build();
        }

        return Response.ok(keyPair).build();
    }

    @GET
    @Path("/user/{username}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getAllKeysForUser(@PathParam("username") String username) {

        try {
            List<SshKeyEntity> keyEntities = enterpriseKeyService.getKeysForUser(username);
            List<KeyDetailsResourceModel> keyResources = new ArrayList<KeyDetailsResourceModel>();
            for (SshKeyEntity keyEntitiy : keyEntities) {
                keyResources.add(KeyDetailsResourceModel.from(keyEntitiy));
            }
            return Response.ok(keyResources).build();
        } catch (EnterpriseKeyGenerationException e) {
            return Response.serverError().build();
        }

    }

}