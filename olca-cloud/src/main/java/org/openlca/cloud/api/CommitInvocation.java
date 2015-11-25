package org.openlca.cloud.api;

import java.io.FileInputStream;
import java.io.IOException;

import org.openlca.core.database.IDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.openlca.cloud.api.data.CommitWriter;
import org.openlca.cloud.util.Directories;
import org.openlca.cloud.util.Strings;
import org.openlca.cloud.util.Valid;
import org.openlca.cloud.util.WebRequests;
import org.openlca.cloud.util.WebRequests.Type;
import org.openlca.cloud.util.WebRequests.WebRequestException;

/**
 * Invokes a web service call to commit data to a repository
 */
public class CommitInvocation extends CommitWriter {

	private static final String PATH = "/commit/";
	private final Logger log = LoggerFactory.getLogger(getClass());
	private String baseUrl;
	private String sessionId;
	private String repositoryId;
	private String lastCommitId;

	CommitInvocation(IDatabase database) {
		super(database);
	}

	void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	void setRepositoryId(String repositoryId) {
		this.repositoryId = repositoryId;
	}

	void setLastCommitId(String lastCommitId) {
		this.lastCommitId = lastCommitId;
	}

	/**
	 * Commits the specified data to the the specified repository
	 * 
	 * @return The id of the commit
	 * @throws WebRequestException
	 *             if user is not in sync with the repository or has no access
	 *             rights to the specified repository. To check if the user is
	 *             in sync, the latest commit id (that id of the last commit
	 *             that was fetched) is send along with the request. If it does
	 *             not match the latest commit id in the repository, the user is
	 *             out of sync
	 */
	String execute() throws WebRequestException {
		Valid.checkNotEmpty(baseUrl, "base url");
		Valid.checkNotEmpty(sessionId, "session id");
		Valid.checkNotEmpty(repositoryId, "repository id");
		if (lastCommitId == null)
			lastCommitId = "null";
		String url = Strings.concat(baseUrl, PATH, repositoryId, "/",
				lastCommitId);
		try {
			close();
			String commitId = WebRequests.call(Type.POST, url, sessionId,
					new FileInputStream(getFile())).getEntity(String.class);
			return commitId;
		} catch (IOException e) {
			log.error("Error cleaning committing data", e);
			return null;
		} finally {
			if (getFile() != null && getFile().getParentFile().exists())
				Directories.delete(getFile().getParentFile());
		}
	}

}
