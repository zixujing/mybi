package com.bi.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/test")
@Controller
public class TestControl {
    @RequestMapping(value = "/haha",method = RequestMethod.GET)
    public String test(){
        String dashboradUrl="http://localhost:8088/superset/explore/?standalone=true&refreshTime=3&form_data=%7B%22datasource%22%3A%221__table%22%2C%22viz_type%22%3A%22line_multi%22%2C%22slice_id%22%3A75%2C%22url_params%22%3A%7B%7D%2C%22time_range%22%3A%221960-01-01+%3A+%22%2C%22color_scheme%22%3A%22bnbColors%22%2C%22prefix_metric_with_slice_name%22%3Atrue%2C%22show_legend%22%3Atrue%2C%22show_markers%22%3Afalse%2C%22line_interpolation%22%3A%22linear%22%2C%22x_axis_label%22%3A%22%22%2C%22bottom_margin%22%3A%22auto%22%2C%22x_ticks_layout%22%3A%22auto%22%2C%22x_axis_format%22%3A%22smart_date%22%2C%22x_axis_showminmax%22%3Afalse%2C%22line_charts%22%3A%5B44%5D%2C%22y_axis_format%22%3A%22.3s%22%2C%22line_charts_2%22%3A%5B57%5D%2C%22y_axis_2_format%22%3A%22.3s%22%2C%22adhoc_filters%22%3A%5B%5D%2C%22annotation_layers%22%3A%5B%5D%7D&height=800";
        return "redirect:"+dashboradUrl;
    }
    @RequestMapping(value = "/ha",method = RequestMethod.GET)
    public String testha(ModelMap map){
        return "welcome";
    }
}
