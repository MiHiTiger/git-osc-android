package net.oschina.gitapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

import net.oschina.gitapp.R;
import net.oschina.gitapp.bean.Issue;
import net.oschina.gitapp.bean.User;
import net.oschina.gitapp.common.StringUtils;
import net.oschina.gitapp.common.UIHelper;

import java.util.List;

/**
 * 项目Issues列表适配器
 * @created 2014-05-28 上午11：19
 * @author 火蚁（http://my.oschina.net/LittleDY）
 * 
 * 最后更新：
 * 更新者：
 */
public class ProjectIssuesListAdapter extends CommonAdapter<Issue> {
	
	public ProjectIssuesListAdapter(Context context, List<Issue> data, int resource) {
		super(context, data, resource);
	}

    @Override
    public void convert(ViewHolder vh, final Issue issue) {
        // 1.加载头像
        String portraitURL = issue.getAuthor() == null ? "" : issue.getAuthor().getNew_portrait();
        if (portraitURL.endsWith("portrait.gif") || StringUtils.isEmpty(portraitURL)) {
            vh.setImageResource(R.id.projectissues_listitem_userface, R.drawable.mini_avatar);
        } else {
            vh.setImageForUrl(R.id.projectissues_listitem_userface, portraitURL);
        }
        vh.getView(R.id.projectissues_listitem_userface).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                User user = issue.getAuthor();
                if (user == null) {
                    return;
                }
                UIHelper.showUserInfoDetail(mContext, user, null);
            }
        });

        // 2.显示相关信息
        vh.setText(R.id.projectissues_listitem_title, issue.getTitle());
        vh.setText(R.id.projectissues_listitem_description, issue.getDescription(), "暂无描述");
        vh.setText(R.id.projectissues_listitem_author, issue.getAuthor() == null ? "" : issue.getAuthor().getName());
        vh.setText(R.id.projectissues_listitem_date, StringUtils.friendly_time(issue.getCreatedAt()));
    }
}