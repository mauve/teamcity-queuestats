package com.spotify.teamcity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;


import jetbrains.buildServer.BuildAgent;
import jetbrains.buildServer.BuildType;
import jetbrains.buildServer.messages.BuildMessage1;
import jetbrains.buildServer.messages.Status;
import jetbrains.buildServer.responsibility.ResponsibilityEntry;
import jetbrains.buildServer.responsibility.TestNameResponsibilityEntry;
import jetbrains.buildServer.serverSide.BuildRevision;
import jetbrains.buildServer.serverSide.BuildServerListener;
import jetbrains.buildServer.serverSide.ResponsibilityInfo;
import jetbrains.buildServer.serverSide.SBuild;
import jetbrains.buildServer.serverSide.SBuildAgent;
import jetbrains.buildServer.serverSide.SBuildType;
import jetbrains.buildServer.serverSide.SFinishedBuild;
import jetbrains.buildServer.serverSide.SProject;
import jetbrains.buildServer.serverSide.SQueuedBuild;
import jetbrains.buildServer.serverSide.SRunningBuild;
import jetbrains.buildServer.serverSide.STest;
import jetbrains.buildServer.serverSide.mute.MuteInfo;
import jetbrains.buildServer.tests.TestName;
import jetbrains.buildServer.users.User;
import jetbrains.buildServer.vcs.SVcsRoot;
import jetbrains.buildServer.vcs.VcsModification;
import jetbrains.buildServer.vcs.VcsRoot;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.Pair;


public class QueueStats implements BuildServerListener {

    private static final Logger LOG = Logger.getInstance(QueueStats.class.getName());
    
    private static final String TYPE = "queueStatistics";
    
    public static final String APP_NAME = "TeamCity";
        
    /**
     * 
     */
    public QueueStats() {
        LOG.debug("Registering QueueStats...");
     }

	@Override
	public void agentDescriptionUpdated(SBuildAgent arg0) {}

	@Override
	public void agentRegistered(SBuildAgent arg0, long arg1) {}

	@Override
	public void agentRemoved(SBuildAgent arg0) {}

	@Override
	public void agentStatusChanged(SBuildAgent arg0, boolean arg1, boolean arg2) {}

	@Override
	public void agentUnregistered(SBuildAgent arg0) {}

	@Override
	public void beforeAgentUnregistered(SBuildAgent arg0) {}

	@Override
	public void beforeBuildFinish(SRunningBuild arg0) {}

	@Override
	public void beforeBuildFinish(SRunningBuild arg0, boolean arg1) {}

	@Override
	public void beforeBuildTypeDeleted(String arg0) {}

	@Override
	public void beforeEntryDelete(SFinishedBuild arg0) {}

	@Override
	public void buildChangedStatus(SRunningBuild arg0, Status arg1, Status arg2) {}

	@Override
	public void buildCommented(SBuild arg0, User arg1, String arg2) {}

	@Override
	public void buildFinished(SRunningBuild arg0) {}

	@Override
	public void buildInterrupted(SRunningBuild arg0) {}

	@Override
	public void buildPinned(SBuild arg0, User arg1, String arg2) {}

	@Override
	public void buildQueueOrderChanged() {}

	@Override
	public void buildRemovedFromQueue(SQueuedBuild arg0, User arg1, String arg2) {}

	@Override
	public void buildStarted(SRunningBuild arg0) {}

	@Override
	public void buildTagsChanged(SBuild arg0, List<String> arg1,
			List<String> arg2) {}

	@Override
	public void buildTagsChanged(SBuild arg0, User arg1, List<String> arg2,
			List<String> arg3) {}

	@Override
	public void buildTypeActiveStatusChanged(SBuildType arg0) {}

	@Override
	public void buildTypeAddedToQueue(SBuildType arg0) {}

	@Override
	public void buildTypeAddedToQueue(SQueuedBuild arg0) {}

	@Override
	public void buildTypeDeleted(String arg0) {}

	@Override
	public void buildTypeMoved(SBuildType arg0, SProject arg1) {}

	@Override
	public void buildTypeRegistered(SBuildType arg0) {}

	@Override
	public void buildTypeTemplateDeleted(String arg0) {}

	@Override
	public void buildTypeUnregistered(SBuildType arg0) {}

	@Override
	public void buildUnpinned(SBuild arg0, User arg1, String arg2) {}

	@Override
	public void changeAdded(VcsModification arg0, VcsRoot arg1,
			Collection<SBuildType> arg2) {}

	@Override
	public void changesLoaded(SRunningBuild arg0) {}

	@Override
	public void cleanupFinished() {}

	@Override
	public void cleanupStarted() {}

	@Override
	public void entryDeleted(SFinishedBuild arg0) {}

	@Override
	public void labelingFailed(SBuild arg0, VcsRoot arg1, Throwable arg2) {}

	@Override
	public void labelingSucceed(SBuild arg0, BuildRevision arg1) {}

	@Override
	public void messageReceived(SRunningBuild arg0, BuildMessage1 arg1) {}

	@Override
	public void pluginsLoaded() {}

	@Override
	public void projectArchived(String arg0) {}

	@Override
	public void projectCreated(String arg0) {}

	@Override
	public void projectDearchived(String arg0) {}

	@Override
	public void projectPersisted(String arg0) {}

	@Override
	public void projectRemoved(String arg0) {}

	@Override
	public void projectRestored(String arg0) {}

	@Override
	public void responsibleChanged(SBuildType arg0, ResponsibilityInfo arg1,
			ResponsibilityInfo arg2, boolean arg3) {}

	@Override
	public void responsibleChanged(SProject arg0,
			TestNameResponsibilityEntry arg1, TestNameResponsibilityEntry arg2,
			boolean arg3) {}

	@Override
	public void responsibleChanged(SProject arg0, Collection<TestName> arg1,
			ResponsibilityEntry arg2, boolean arg3) {}

	@Override
	public void serverConfigurationReloaded() {}

	@Override
	public void serverShutdown() {}

	@Override
	public void serverShutdownComplete() {}

	@Override
	public void serverStartup() {}

	@Override
	public void sourcesVersionReleased(BuildAgent arg0) {}

	@Override
	public void sourcesVersionReleased(BuildType arg0) {}

	@Override
	public void sourcesVersionReleased(BuildType arg0, BuildAgent arg1) {}

	@Override
	public void testsMuted(MuteInfo arg0) {}

	@Override
	public void testsUnmuted(SProject arg0,
			Collection<Pair<MuteInfo, Collection<STest>>> arg1) {}

	@Override
	public void vcsRootRemoved(SVcsRoot arg0) {}

	@Override
	public void vcsRootUpdated(SVcsRoot arg0, SVcsRoot arg1) {}

	@Override
	public void vcsRootsPersisted() {}

}
