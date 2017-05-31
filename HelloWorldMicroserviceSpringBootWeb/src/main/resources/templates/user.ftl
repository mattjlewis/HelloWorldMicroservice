<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Edit User">
	<section>
		<form action="/user/${user.id}" method="post">
			<div class="field">
				<label for="name">Name: </label><input type="text" id="name" name="name" value="${user.name}" required />
			</div>
			<div class="field">
				<label for="email">Email Address: </label><input type="text" id="email" name="email" value="${user.email}" required />
			</div>
			<ul class="actions">
				<li><input type="submit" value="Update" /></li>
			</ul>
		</form>
	</section>
</@layout.masterTemplate>
