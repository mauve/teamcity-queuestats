<%@ include file="/include.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bs" tagdir="/WEB-INF/tags" %>
<script type="text/javascript">
    function sendTest() {

        var nick = $('properties[ircNotifier.Nickname].value');

        var gm = $('ircTestMessage').value;
        if(!gm || gm.length ==0) {
            return;
        }

        BS.ajaxRequest($('ircTestForm').action, {
            parameters: 'nickname='+ nick.value,
            onComplete: function(transport) {
              if (transport.responseXML) {
                  $('ircTestForm').refresh();
              }
            }
        });
        return false;
    }
</script>

<h3 style="margin-top: 1em">Queue Length by Agent</h3>
<table cellspacing="0" class="dark sortable borderBottom">
	<thead>
		<tr>
			<th>Agent Name</th>
			<th>Average Queue Time</th>
			<th>Max Queue Time</th>
			<th>Number of Samples</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="entry" items="${byAgents}">
		<tr>
			<td>${entry.name}</td>
			<td>${entry.averageQueueTime}</td>
			<td>${entry.maxQueueTime}</td>
			<td>${entry.count}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>

<h3 style="margin-top: 1em">Queue Length by Project</h3>
<table cellspacing="0" class="dark sortable borderBottom">
	<thead>
		<tr>
			<th>Project Name</th>
			<th>Average Queue Time</th>
			<th>Max Queue Time</th>
			<th>Number of Samples</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="entry" items="${byProject}">
		<tr>
			<td>${entry.name}</td>
			<td>${entry.averageQueueTime}</td>
			<td>${entry.maxQueueTime}</td>
			<td>${entry.count}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>

<h3 style="margin-top: 1em">Queue Length by Build Type</h3>
<table cellspacing="0" class="dark sortable borderBottom">
	<thead>
		<tr>
			<th>Build Name</th>
			<th>Average Queue Time</th>
			<th>Max Queue Time</th>
			<th>Number of Samples</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="entry" items="${byBuilds}">
		<tr>
			<td>${entry.name}</td>
			<td>${entry.averageQueueTime}</td>
			<td>${entry.maxQueueTime}</td>
			<td>${entry.count}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
