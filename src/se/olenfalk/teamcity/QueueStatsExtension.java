package se.olenfalk.teamcity;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.intellij.openapi.diagnostic.Logger;

import jetbrains.buildServer.util.ItemProcessor;
import jetbrains.buildServer.web.openapi.CustomTab;
import jetbrains.buildServer.web.openapi.PagePlaces;
import jetbrains.buildServer.web.openapi.PlaceId;
import jetbrains.buildServer.web.openapi.SimplePageExtension;
import jetbrains.buildServer.serverSide.BuildHistory;
import jetbrains.buildServer.serverSide.SFinishedBuild;

public class QueueStatsExtension
				extends SimplePageExtension
				implements CustomTab
{
	private static final Logger LOG = Logger.getInstance(QueueStatsExtension.class.getName());

	/**
	 * Look back this number of days for the statistics.
	 */
	private static final int HISTORY_DAYS = 60;

	/**
	 * Jobs enqueued before this time will be ignored.
	 *
	 * Computed from {@link #HISTORY_DAYS}.
	 */
	private Date cutoff;

	private BuildHistory buildHistory;

	public QueueStatsExtension(PagePlaces pagePlaces,
							   BuildHistory bh) {
		super(pagePlaces);
		setIncludeUrl("queuestats.jsp");
		setPlaceId(PlaceId.AGENTS_TAB);
		setPluginName("queuestats");
		register();

		buildHistory = bh;
		LOG.info("BuildHistory: " + (buildHistory == null ? "null" : "YEAH"));

		cutoff = new Date(System.currentTimeMillis() - HISTORY_DAYS * 24L * 60L * 60L * 1000L);
	}

	@Override
	public String getTabId() {
		return "queuestats";
	}

	@Override
	public String getTabTitle() {
		return "Queue Statistics";
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public void fillModel(Map<String, Object> model, HttpServletRequest request) {
		super.fillModel(model, request);

		final AvrDiffTimeMap byAgents = new AvrDiffTimeMap();
		final AvrDiffTimeMap byBuilds = new AvrDiffTimeMap();

		buildHistory.processEntries(new ItemProcessor<SFinishedBuild>() {
			@Override
			public boolean processItem(SFinishedBuild build) {
				if (build.getQueuedDate().before(cutoff)) {
					return true;
				}

				Calendar cal = Calendar.getInstance();

				cal.setTime(build.getQueuedDate());
				long start = cal.getTimeInMillis();
				
				cal.setTime(build.getFinishDate());
				long stop = cal.getTimeInMillis() - build.getDuration();
				
				long diff = stop - start;

				byAgents.put(build.getAgentName(), diff);
				byBuilds.put(build.getFullName(), diff);

				return true;
			}
		});

		model.put("byAgents", byAgents.getFormattedMap());
		model.put("byBuilds", byBuilds.getFormattedMap());
	}
}
