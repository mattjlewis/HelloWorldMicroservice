<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Hello">
	<ul>
		<li><strong>O/S</strong>: ${os_name}</li>
		<li><strong>Hostname</strong>: ${hostname}</li>
		<#if page??><li><strong>Custom Page Var</strong>: ${page}</li></#if>
		<#if current_template_name??><li>Current Template: ${current_template_name}</li></#if>
		<#if main_template_name??><li>Main Template: ${main_template_name}</li></#if>
		<#if data_model??><li>Data model: ${data_model}</li></#if>
		<#if vars??><li>Vars: ${vars}</li></#if>
	</ul>
</@layout.masterTemplate>
