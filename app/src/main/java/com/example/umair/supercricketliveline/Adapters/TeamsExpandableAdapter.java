package com.example.umair.supercricketliveline.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.umair.supercricketliveline.R;
import com.example.umair.supercricketliveline.POJOClasses.TeamPlayers;

import java.util.HashMap;
import java.util.List;

public class TeamsExpandableAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<String> mGroupHeaders;
    HashMap<String,List<TeamPlayers>> mTeamList;

    public TeamsExpandableAdapter(Context mContext, List<String> mGroupHeaders, HashMap<String, List<TeamPlayers>> mTeamList) {
        this.mContext = mContext;
        this.mGroupHeaders = mGroupHeaders;
        this.mTeamList = mTeamList;
    }

    @Override
    public int getGroupCount() {
        return mGroupHeaders.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return this.mTeamList.get(this.mGroupHeaders.get(i))
                .size();
    }

    @Override
    public Object getGroup(int i) {
        return mGroupHeaders.get(i);
    }

    @Override
    public Object getChild(int gp, int cp) {
        return this.mTeamList.get(this.mGroupHeaders.get(gp))
                .get(cp);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int cp) {
        return cp;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean b, View convertView, ViewGroup viewGroup) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(int i, int i1, boolean isLastChild, View view, ViewGroup viewGroup) {
        TeamPlayers pl = (TeamPlayers) getChild(i,i1);
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.player_list_itemview, null);
        }
        TextView playerNameView = view.findViewById(R.id.txt_player_name);
        TextView playTypeView = view.findViewById(R.id.txt_player_type);
        playerNameView.setText(pl.getPlayer_name());
        playTypeView.setText(pl.getPlayer_type());
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
