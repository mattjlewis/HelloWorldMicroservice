<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Users">
	<#if error??>
	<p>Error: ${error}.</p>
	</#if>
	<#if message??>
	<p>Error message: ${message}.</p>
	</#if>
	<#if user??>
	<p>User '${user.name}' (${user.email}) saved with id ${user.id}.</p>
	</#if>
	<div class="table-wrapper">
		<table>
			<thead>
				<tr><th>Id</th><th>Name</th><th>Email Address</th></tr>
			</thead>
			<tbody>
				<#list users as user><tr><td><a href="/user/${user.id}">${user.id}</a></td><td>${user.name}</td><td>${user.email}</td></tr></#list>
			</tbody>
		</table>
	</div>
	<section>
		<h2>New User</h2>
		<form action="/user" method="post">
			<div class="field">
				<input type="text" id="name" name="name" placeholder="Name" required />
			</div>
			<div class="field">
				<input type="email" id="email" name="email" placeholder="Email" required />
			</div>
			<ul class="actions">
				<li><input type="submit" value="Create" /></li>
				<li><input type="reset" value="Reset" /></li>
			</ul>
		</form>
	</section>
</@layout.masterTemplate>
