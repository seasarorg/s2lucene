package org.seasar.lucene.command.impl;

import java.util.ArrayList;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.lucene.command.LuceneCommand;
import org.seasar.lucene.command.LuceneCommandFactory;
import org.seasar.lucene.common.CommandType;

public class LuceneCommandFactoryImpl implements LuceneCommandFactory {

	private List<LuceneCommand> commands = new ArrayList<LuceneCommand>();

	private String searchCommandTypeSuffix;
	private String createCommandTypeSuffix;
	private String updateCommandTypeSuffix;
	private String deleteCommandTypeSuffix;

	public void addCommand(LuceneCommand luceneCommand) {
		commands.add(luceneCommand);
	}

	public LuceneCommand getCommand(MethodInvocation invocation) {
		String invokeMethodName = invocation.getMethod().getName();
		CommandType invokeCommandType = checkCommandType(invokeMethodName);
		for (LuceneCommand luceneCommand : commands) {
			if (luceneCommand.getTypeName() == invokeCommandType) {
				return luceneCommand;
			}
		}
		throw new RuntimeException("Command is Nulll");
	}

	private CommandType checkCommandType(String invokeMethodName) {
		if (invokeMethodName.startsWith(deleteCommandTypeSuffix)) {
			return CommandType.DELETE;
		}
		if (invokeMethodName.startsWith(updateCommandTypeSuffix)) {
			return CommandType.UPDATE;
		}
		if (invokeMethodName.startsWith(createCommandTypeSuffix)) {
			return CommandType.CREATE;
		}
		if (invokeMethodName.startsWith(searchCommandTypeSuffix)) {
			return CommandType.SEARCH;
		}
		throw new RuntimeException("InvokeMethod Name is Valid");
	}

	public String getSearchCommandTypeSuffix() {
		return searchCommandTypeSuffix;
	}

	public void setSearchCommandTypeSuffix(String searchCommandTypeSuffix) {
		this.searchCommandTypeSuffix = searchCommandTypeSuffix;
	}

	public String getCreateCommandTypeSuffix() {
		return createCommandTypeSuffix;
	}

	public void setCreateCommandTypeSuffix(String createCommandTypeSuffix) {
		this.createCommandTypeSuffix = createCommandTypeSuffix;
	}

	public String getUpdateCommandTypeSuffix() {
		return updateCommandTypeSuffix;
	}

	public void setUpdateCommandTypeSuffix(String updateCommandTypeSuffix) {
		this.updateCommandTypeSuffix = updateCommandTypeSuffix;
	}

	public String getDeleteCommandTypeSuffix() {
		return deleteCommandTypeSuffix;
	}

	public void setDeleteCommandTypeSuffix(String deleteCommandTypeSuffix) {
		this.deleteCommandTypeSuffix = deleteCommandTypeSuffix;
	}

}
