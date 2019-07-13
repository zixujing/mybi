package org.smallbun.fast.manage.mybi.controller;

import org.apache.ibatis.annotations.Param;
import org.smallbun.fast.manage.mybi.entity.ParameterEntity;
import org.smallbun.fast.manage.mybi.entity.UrlForTable;
import org.smallbun.framework.toolkit.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/mybi")
public class MyBIController {
    @RequestMapping()
    public ModelAndView mybi()  {

        return new ModelAndView("modules/manage/mybi/mybi.html");
    }
    /**
     * 网商详情页面
     * @return 页面路径
     */
    @RequestMapping(value = "mywsdetail")
    public String myWsDetail() throws UnsupportedEncodingException {
        ParameterEntity parameterEntity=new ParameterEntity();
        String params="{\"datasource\": \"12__table\", \"viz_type\": \"table\", \"slice_id\": 75, \"url_params\": {}, \"granularity_sqla\": null, \"time_grain_sqla\": \"PT1H\", \"time_range\": \"Last week\", \"groupby\": [], \"metrics\": [], \"percent_metrics\": [], \"timeseries_limit_metric\": [], \"row_limit\": 10000, \"include_time\": false, \"order_desc\": true, \"all_columns\": [\"id\", \"name\", \"xz_name\", \"zb\"], \"order_by_cols\": [\"[\\\"id\\\", true]\"], \"adhoc_filters\": [], \"table_timestamp_format\": \"%Y-%m-%d %H:%M:%S\", \"page_length\": 10, \"include_search\": true, \"table_filter\": true, \"align_pn\": true, \"color_pn\": true}";
        parameterEntity.setForm_data(params);
        UrlForTable urlForTable=new UrlForTable();
        return "redirect:"+urlForTable.getBasUrl()+parameterEntity.getParamsOrderByKey();
    }
    @RequestMapping(value = "wsdetail")
    public ModelAndView wsDetail()  {
        return new ModelAndView("modules/manage/mybi/wsdetail.html");
    }
    @RequestMapping(value = "mydprank",method = RequestMethod.GET)
    public String mydpRank(@Param("rowlimit") String rowlimit,@Param("timerange") String timerange) throws UnsupportedEncodingException {

        String params="{\"datasource\": \"15__table\", \"viz_type\": \"table\", \"slice_id\": 89, \"url_params\": {}, \"granularity_sqla\": \"ri_id\", \"time_grain_sqla\": \"P1D\", \"time_range\": \"Last month\", \"groupby\": [\"dp_name\"], \"metrics\": [\"\\u9500\\u552e\\u989d\"], \"percent_metrics\": [], \"timeseries_limit_metric\": null, \"row_limit\": 10000, \"include_time\": false, \"order_desc\": true, \"all_columns\": [], \"order_by_cols\": [], \"adhoc_filters\": [], \"table_timestamp_format\": \"%Y-%m-%d %H:%M:%S\", \"page_length\": 10, \"include_search\": true, \"table_filter\": true, \"align_pn\": true, \"color_pn\": true}";
        ParameterEntity parameterEntity=new ParameterEntity();
        parameterEntity.setForm_data(params);
        UrlForTable urlForTable=new UrlForTable();

        if(StringUtils.hasText(timerange)){
            parameterEntity.setPars("time_range",timerange);

        }
        if(StringUtils.hasText(rowlimit)){
            parameterEntity.setPars("row_limit",Integer.parseInt(rowlimit));
        }

        return "redirect:"+urlForTable.getBasUrl()+parameterEntity.getParamsOrderByKey();
    }

    @RequestMapping(value = "dprank",method = RequestMethod.GET)
    public ModelAndView dpRank(@Param("rowlimit") String rowlimit, @Param("timerange") String timerange, Model model)  {
        model.addAttribute("rowlimit", rowlimit);
        model.addAttribute("timerange", timerange);
        ModelAndView modelAndView=new ModelAndView("modules/manage/mybi/dprank.html");
        return modelAndView;
    }

    @RequestMapping(value = "myareadis",method = RequestMethod.GET)
    public String myareaDis(@Param("rowlimit") String rowlimit,@Param("timerange") String timerange,
                            @Param("pic") String pic) throws UnsupportedEncodingException {
        String params;
        String pic_rose="{\"datasource\": \"13__table\", \"viz_type\": \"rose\", \"slice_id\": 88, \"url_params\": {}, \"granularity_sqla\": \"ri_id\", \"time_grain_sqla\": \"P1D\", \"time_range\": \"Last week\", \"metrics\": [{\"expressionType\": \"SIMPLE\", \"column\": {\"id\": 433, \"column_name\": \"zj\", \"verbose_name\": null, \"description\": null, \"expression\": \"\", \"filterable\": false, \"groupby\": false, \"is_dttm\": false, \"type\": \"DECIMAL(32, 0)\", \"database_expression\": null, \"python_date_format\": null, \"optionName\": \"_col_zj\"}, \"aggregate\": \"SUM\", \"sqlExpression\": null, \"hasCustomLabel\": false, \"fromFormData\": true, \"label\": \"SUM(zj)\", \"optionName\": \"metric_kuv60i2itrm_axv1k2ega9s\"}], \"adhoc_filters\": null, \"groupby\": [\"xz_name\"], \"timeseries_limit_metric\": null, \"order_desc\": true, \"contribution\": false, \"row_limit\": 10000, \"color_scheme\": \"bnbColors\", \"number_format\": \".4r\", \"date_time_format\": \"%d/%m/%Y\", \"rich_tooltip\": true, \"rose_area_proportion\": false, \"rolling_type\": null, \"comparison_type\": \"values\", \"resample_how\": null, \"resample_rule\": null, \"resample_fillmethod\": null}";
        String pic_sanky="{\"datasource\": \"13__table\", \"viz_type\": \"sankey\", \"slice_id\": 87, \"url_params\": {}, \"granularity_sqla\": \"ri_id\", \"time_grain_sqla\": \"P1D\", \"time_range\": \"Last week\", \"groupby\": [\"ri_name\", \"xz_name\"], \"metric\": {\"expressionType\": \"SIMPLE\", \"column\": {\"id\": 433, \"column_name\": \"zj\", \"verbose_name\": null, \"description\": null, \"expression\": \"\", \"filterable\": false, \"groupby\": false, \"is_dttm\": false, \"type\": \"DECIMAL(32, 0)\", \"database_expression\": null, \"python_date_format\": null, \"optionName\": \"_col_zj\"}, \"aggregate\": \"SUM\", \"sqlExpression\": null, \"hasCustomLabel\": false, \"fromFormData\": false, \"label\": \"SUM(zj)\", \"optionName\": \"metric_cymcdsllz2h_w9oo8iy6dkn\"}, \"adhoc_filters\": [], \"row_limit\": 10000, \"color_scheme\": \"bnbColors\"}";
        String pic_pie="{\"datasource\": \"13__table\", \"viz_type\": \"pie\", \"url_params\": {}, \"granularity_sqla\": \"rq_id\", \"time_grain_sqla\": \"P1D\", \"time_range\": \"Last day\", \"metric\": {\"expressionType\": \"SIMPLE\", \"column\": {\"id\": 433, \"column_name\": \"zj\", \"verbose_name\": null, \"description\": null, \"expression\": \"\", \"filterable\": false, \"groupby\": false, \"is_dttm\": false, \"type\": \"DECIMAL(32, 0)\", \"database_expression\": null, \"python_date_format\": null, \"optionName\": \"_col_zj\"}, \"aggregate\": \"SUM\", \"sqlExpression\": null, \"hasCustomLabel\": false, \"fromFormData\": false, \"label\": \"SUM(zj)\", \"optionName\": \"metric_zlibotdcki_2oi46t73cvi\"}, \"adhoc_filters\": [], \"groupby\": [\"xz_name\"], \"row_limit\": 10000, \"pie_label_type\": \"key_percent\", \"donut\": true, \"show_legend\": true, \"show_labels\": true, \"labels_outside\": true, \"color_scheme\": \"bnbColors\"}";
        String pic_area="{\"datasource\": \"13__table\", \"viz_type\": \"area\", \"url_params\": {}, \"granularity_sqla\": \"ri_id\", \"time_grain_sqla\": \"P1D\", \"time_range\": \"Last week\", \"metrics\": [{\"expressionType\": \"SIMPLE\", \"column\": {\"id\": 433, \"column_name\": \"zj\", \"verbose_name\": null, \"description\": null, \"expression\": \"\", \"filterable\": false, \"groupby\": false, \"is_dttm\": false, \"type\": \"DECIMAL(32, 0)\", \"database_expression\": null, \"python_date_format\": null, \"optionName\": \"_col_zj\"}, \"aggregate\": \"SUM\", \"sqlExpression\": null, \"hasCustomLabel\": false, \"fromFormData\": true, \"label\": \"SUM(zj)\", \"optionName\": \"metric_4p049peiy69_rfi5m7of7z\"}], \"adhoc_filters\": [], \"groupby\": [\"xz_name\"], \"timeseries_limit_metric\": null, \"order_desc\": true, \"contribution\": false, \"row_limit\": 10000, \"show_brush\": \"auto\", \"show_legend\": true, \"line_interpolation\": \"step-after\", \"stacked_style\": \"stream\", \"color_scheme\": \"bnbColors\", \"rich_tooltip\": true, \"show_controls\": false, \"x_axis_label\": \"\\u65e5\\u671f\", \"bottom_margin\": \"auto\", \"x_ticks_layout\": \"auto\", \"x_axis_format\": \"%Y-%m-%d\", \"x_axis_showminmax\": false, \"y_axis_format\": \".4r\", \"y_axis_bounds\": [null, null], \"y_log_scale\": false, \"rolling_type\": \"None\", \"time_compare\": [], \"comparison_type\": \"values\", \"resample_how\": null, \"resample_rule\": null, \"resample_fillmethod\": null, \"annotation_layers\": []}";
        String pic_bar="{\"datasource\": \"13__table\", \"viz_type\": \"bar\", \"url_params\": {}, \"granularity_sqla\": \"ri_id\", \"time_grain_sqla\": null, \"time_range\": \"Last week\", \"metrics\": [{\"expressionType\": \"SQL\", \"sqlExpression\": \"SUM(zj)\", \"column\": null, \"aggregate\": null, \"hasCustomLabel\": false, \"fromFormData\": true, \"label\": \"SUM(zj)\", \"optionName\": \"metric_uf1z6walup8_qgqku92nqz\"}], \"adhoc_filters\": [], \"groupby\": [\"xz_name\"], \"timeseries_limit_metric\": null, \"order_desc\": true, \"contribution\": false, \"row_limit\": 10000, \"color_scheme\": \"bnbColors\", \"show_brush\": \"auto\", \"show_legend\": true, \"show_bar_value\": false, \"rich_tooltip\": true, \"bar_stacked\": true, \"line_interpolation\": \"linear\", \"show_controls\": false, \"bottom_margin\": \"auto\", \"x_axis_label\": \"\\u65e5\\u671f\", \"x_ticks_layout\": \"auto\", \"x_axis_format\": \"%Y-%m-%d\", \"x_axis_showminmax\": true, \"reduce_x_ticks\": true, \"y_axis_label\": \"\\u9500\\u552e\\u989d\", \"left_margin\": \"auto\", \"y_axis_showminmax\": true, \"y_log_scale\": false, \"y_axis_format\": \".4r\", \"y_axis_bounds\": [null, null], \"rolling_type\": \"None\", \"comparison_type\": \"values\", \"resample_how\": null, \"resample_rule\": null, \"resample_fillmethod\": null, \"annotation_layers\": []}";
        if(!StringUtils.hasText(pic)){
            params=pic_rose;
        }else if(pic.equals("rose")){
            params=pic_rose;
        }else if(pic.equals("sanky")){
            params=pic_sanky;
        }else if(pic.equals("pie")){
            params=pic_pie;
        }else if(pic.equals("area")){
            params=pic_area;
        }else if(pic.equals("bar")){
            params=pic_bar;
        }else {
            params=pic_rose;
        }
        ParameterEntity parameterEntity=new ParameterEntity();
        parameterEntity.setForm_data(params);
        UrlForTable urlForTable=new UrlForTable();

        if(StringUtils.hasText(timerange)){
            parameterEntity.setPars("time_range",timerange);

        }
        if(StringUtils.hasText(rowlimit)){
            parameterEntity.setPars("row_limit",Integer.parseInt(rowlimit));
        }

        return "redirect:"+urlForTable.getBasUrl()+parameterEntity.getParamsOrderByKey();
    }

    @RequestMapping(value = "areadis",method = RequestMethod.GET)
    public ModelAndView areaDis(@Param("rowlimit") String rowlimit, @Param("timerange") String timerange,
                                @Param("pic") String pic, Model model)  {
        model.addAttribute("rowlimit", rowlimit);
        model.addAttribute("timerange", timerange);
        model.addAttribute("pic", pic);
        ModelAndView modelAndView=new ModelAndView("modules/manage/mybi/areadis.html");
        return modelAndView;
    }




    @RequestMapping(value = "mydatadetail",method = RequestMethod.GET)
    public String mydataDetail(@Param("rowlimit") String rowlimit,@Param("timerange") String timerange,
                            @Param("pic") String pic) throws UnsupportedEncodingException {
        String params;
        String pic_hour="{\"datasource\": \"14__table\", \"viz_type\": \"dist_bar\", \"slice_id\": 83, \"url_params\": {}, \"granularity_sqla\": \"ri_id\", \"time_grain_sqla\": \"P1D\", \"time_range\": \"Last week\", \"metrics\": [{\"expressionType\": \"SIMPLE\", \"column\": {\"id\": 447, \"column_name\": \"zj\", \"verbose_name\": \"\\u9500\\u552e\\u989d\", \"description\": \"\", \"expression\": \"\", \"filterable\": false, \"groupby\": false, \"is_dttm\": false, \"type\": \"DECIMAL(32, 0)\", \"database_expression\": \"\", \"python_date_format\": \"\", \"optionName\": \"_col_zj\"}, \"aggregate\": \"SUM\", \"sqlExpression\": null, \"hasCustomLabel\": false, \"fromFormData\": false, \"label\": \"SUM(zj)\", \"optionName\": \"metric_1zt9mo1uegp_eklqa9emek5\"}], \"adhoc_filters\": [], \"groupby\": [\"ri_id\"], \"columns\": [\"name\"], \"row_limit\": 10000, \"contribution\": false, \"color_scheme\": \"bnbColors\", \"show_legend\": false, \"show_bar_value\": true, \"bar_stacked\": true, \"order_bars\": true, \"y_axis_format\": \".4r\", \"y_axis_label\": \"\\u9500\\u552e\\u989d\", \"show_controls\": false, \"x_axis_label\": \"\\u65e5\\u671f\", \"bottom_margin\": \"auto\", \"x_ticks_layout\": \"flat\", \"reduce_x_ticks\": false}";
        String pic_day="{\"datasource\": \"14__table\", \"viz_type\": \"dist_bar\", \"slice_id\": 80, \"url_params\": {}, \"granularity_sqla\": \"ri_id\", \"time_grain_sqla\": \"P1D\", \"time_range\": \"Last week\", \"metrics\": [\"\\u9500\\u552e\\u989d\"], \"adhoc_filters\": [], \"groupby\": [\"ri_id\"], \"columns\": [], \"row_limit\": 10000, \"contribution\": false, \"color_scheme\": \"bnbColors\", \"show_legend\": true, \"show_bar_value\": true, \"bar_stacked\": true, \"order_bars\": true, \"y_axis_format\": \".4r\", \"y_axis_label\": \"\\u9500\\u552e\\u989d\", \"show_controls\": false, \"x_axis_label\": \"\\u65e5\\u671f\", \"bottom_margin\": \"auto\", \"x_ticks_layout\": \"staggered\", \"reduce_x_ticks\": true}";
        String pic_line="{\"datasource\": \"14__table\", \"viz_type\": \"line\", \"slice_id\": 79, \"url_params\": {}, \"granularity_sqla\": \"\\u65e5\\u671f\", \"time_grain_sqla\": null, \"time_range\": \"Last 3 days\", \"metrics\": [\"\\u9500\\u552e\\u989d\"], \"adhoc_filters\": [], \"groupby\": [], \"limit\": 100, \"timeseries_limit_metric\": null, \"order_desc\": true, \"contribution\": false, \"row_limit\": 50000, \"color_scheme\": \"lyftColors\", \"show_brush\": \"auto\", \"send_time_range\": true, \"show_legend\": true, \"rich_tooltip\": true, \"show_markers\": true, \"line_interpolation\": \"linear\", \"x_axis_label\": \"\\u65f6\\u95f4\\uff08\\u5c0f\\u65f6\\uff09\", \"bottom_margin\": \"auto\", \"x_ticks_layout\": \"auto\", \"x_axis_format\": \"%H:%M:%S\", \"x_axis_showminmax\": true, \"y_axis_label\": \"\\u9500\\u91cf\\uff08\\u5143\\uff09\", \"left_margin\": \"auto\", \"y_axis_showminmax\": true, \"y_log_scale\": true, \"y_axis_format\": \"+,\", \"y_axis_bounds\": [null, null], \"rolling_type\": null, \"time_compare\": [\"1 day\"], \"comparison_type\": \"values\", \"resample_how\": null, \"resample_rule\": null, \"resample_fillmethod\": null, \"annotation_layers\": []}";
        if(!StringUtils.hasText(pic)){
            params=pic_hour;
        }else if(pic.equals("hour")){
            params=pic_hour;
        }else if(pic.equals("day")){
            params=pic_day;
        }else if(pic.equals("line")){
            params=pic_line;
        }else {
            params=pic_hour;
        }
        ParameterEntity parameterEntity=new ParameterEntity();
        parameterEntity.setForm_data(params);
        UrlForTable urlForTable=new UrlForTable();

        if(StringUtils.hasText(timerange)){
            parameterEntity.setPars("time_range",timerange);

        }
        if(StringUtils.hasText(rowlimit)){
            parameterEntity.setPars("row_limit",Integer.parseInt(rowlimit));
        }

        return "redirect:"+urlForTable.getBasUrl()+parameterEntity.getParamsOrderByKey();
    }

    @RequestMapping(value = "datadetail",method = RequestMethod.GET)
    public ModelAndView dateDetail(@Param("rowlimit") String rowlimit, @Param("timerange") String timerange,
                                @Param("pic") String pic, Model model)  {
        model.addAttribute("rowlimit", rowlimit);
        model.addAttribute("timerange", timerange);
        model.addAttribute("pic", pic);
        ModelAndView modelAndView=new ModelAndView("modules/manage/mybi/datadetail.html");
        return modelAndView;
    }





    // ==============================================================================================================

    @RequestMapping(value = "mybignumber",method = RequestMethod.GET)
    public String mybigNumber(@Param("rowlimit") String rowlimit,@Param("timerange") String timerange,
                               @Param("pic") String pic) throws UnsupportedEncodingException {
        String params;
        String pic_zuorizonge="{\"datasource\": \"14__table\", \"viz_type\": \"big_number_total\", \"slice_id\": 81, \"url_params\": {}, \"granularity_sqla\": \"ri_id\", \"time_grain_sqla\": \"P1D\", \"time_range\": \"Last day\", \"metric\": \"\\u9500\\u552e\\u989d\", \"adhoc_filters\": [], \"subheader\": \"\", \"y_axis_format\": \".4r\"}";
        String pic_zuoriyuqianribi="{\"datasource\": \"14__table\", \"viz_type\": \"big_number\", \"slice_id\": 82, \"url_params\": {}, \"granularity_sqla\": \"ri_id\", \"time_grain_sqla\": \"P1D\", \"time_range\": \"Last week\", \"metric\": \"\\u9500\\u552e\\u989d\", \"adhoc_filters\": [], \"compare_lag\": 1, \"compare_suffix\": \"\", \"y_axis_format\": \".4r\", \"show_trend_line\": true, \"start_y_axis_at_zero\": true, \"color_picker\": {\"r\": 0, \"g\": 122, \"b\": 135, \"a\": 1}}";
        String pic_yuetongbi="{\"datasource\": \"16__table\", \"viz_type\": \"big_number\", \"slice_id\": 96, \"url_params\": {}, \"granularity_sqla\": \"\\u65e5\\u671f\", \"time_grain_sqla\": \"P0.25Y\", \"time_range\": \"Last 2 months\", \"metric\": {\"expressionType\": \"SIMPLE\", \"column\": {\"id\": 473, \"column_name\": \"zj\", \"verbose_name\": null, \"description\": null, \"expression\": \"\", \"filterable\": false, \"groupby\": false, \"is_dttm\": false, \"type\": \"DECIMAL(54, 0)\", \"database_expression\": null, \"python_date_format\": null, \"optionName\": \"_col_zj\"}, \"aggregate\": \"SUM\", \"sqlExpression\": null, \"hasCustomLabel\": false, \"fromFormData\": false, \"label\": \"SUM(zj)\", \"optionName\": \"metric_4al091s1ftx_e2p5q3r6ud8\"}, \"adhoc_filters\": [], \"compare_lag\": 1, \"compare_suffix\": \"\\u540c\\u6bd4\\u589e\\u957f\", \"y_axis_format\": \".4r\", \"show_trend_line\": false, \"start_y_axis_at_zero\": true, \"color_picker\": {\"r\": 0, \"g\": 122, \"b\": 135, \"a\": 1}}";
        String pic_ritongbi="{\"datasource\": \"15__table\", \"viz_type\": \"big_number\", \"slice_id\": 95, \"url_params\": {}, \"granularity_sqla\": \"\\u65e5\\u671f\\u8fc7\\u6ee4\", \"time_grain_sqla\": null, \"time_range\": \"Last 3 days\", \"metric\": \"\\u9500\\u552e\\u989d\", \"adhoc_filters\": [], \"compare_lag\": 1, \"compare_suffix\": \"\\u540c\\u6bd4\\u589e\\u957f\", \"y_axis_format\": \".4r\", \"show_trend_line\": false, \"start_y_axis_at_zero\": true, \"color_picker\": {\"r\": 255, \"g\": 90, \"b\": 95, \"a\": 1}}";
        if(!StringUtils.hasText(pic)){
            params=pic_yuetongbi;
        }else if(pic.equals("zuorizonge")){
            params=pic_zuorizonge;
        }else if(pic.equals("zuoriyuqianribi")){
            params=pic_zuoriyuqianribi;
        }else if(pic.equals("yuetongbi")){
            params=pic_yuetongbi;
        }else if(pic.equals("ritongbi")){
            params=pic_ritongbi;
        }else {
            params=pic_yuetongbi;
        }
        ParameterEntity parameterEntity=new ParameterEntity();
        parameterEntity.setForm_data(params);
        UrlForTable urlForTable=new UrlForTable();

        if(StringUtils.hasText(timerange)){
            parameterEntity.setPars("time_range",timerange);

        }
        if(StringUtils.hasText(rowlimit)){
            parameterEntity.setPars("row_limit",Integer.parseInt(rowlimit));
        }

        return "redirect:"+urlForTable.getBasUrl()+parameterEntity.getParamsOrderByKey();
    }

    @RequestMapping(value = "bignumber",method = RequestMethod.GET)
    public ModelAndView bigNumber(@Param("rowlimit") String rowlimit, @Param("timerange") String timerange,
                                   @Param("pic") String pic, Model model)  {
        model.addAttribute("rowlimit", rowlimit);
        model.addAttribute("timerange", timerange);
        model.addAttribute("pic", pic);
        ModelAndView modelAndView=new ModelAndView("modules/manage/mybi/bignumber.html");
        return modelAndView;
    }


    //=====================================================================================

    @RequestMapping(value = "myclasszb",method = RequestMethod.GET)
    public String myclassZb(@Param("rowlimit") String rowlimit,@Param("timerange") String timerange) throws UnsupportedEncodingException {

        String params="{\"datasource\": \"15__table\", \"viz_type\": \"dist_bar\", \"url_params\": {}, \"granularity_sqla\": \"ri_id\", \"time_grain_sqla\": \"P1D\", \"time_range\": \"Last week\", \"metrics\": [\"\\u9500\\u552e\\u989d\"], \"adhoc_filters\": [], \"groupby\": [\"xz_name\"], \"columns\": [\"fl_name\"], \"row_limit\": 10000, \"contribution\": true, \"color_scheme\": \"bnbColors\", \"show_legend\": false, \"show_bar_value\": false, \"bar_stacked\": true, \"order_bars\": false, \"y_axis_format\": \".3s\", \"y_axis_label\": \"\\u9500\\u552e\\u989d\\u5360\\u6bd4\", \"show_controls\": false, \"x_axis_label\": \"\\u5730\\u57df\", \"bottom_margin\": \"auto\", \"x_ticks_layout\": \"auto\", \"reduce_x_ticks\": true}";
        ParameterEntity parameterEntity=new ParameterEntity();
        parameterEntity.setForm_data(params);
        UrlForTable urlForTable=new UrlForTable();

        if(StringUtils.hasText(timerange)){
            parameterEntity.setPars("time_range",timerange);

        }
        if(StringUtils.hasText(rowlimit)){
            parameterEntity.setPars("row_limit",Integer.parseInt(rowlimit));
        }

        return "redirect:"+urlForTable.getBasUrl()+parameterEntity.getParamsOrderByKey();
    }

    @RequestMapping(value = "classzb",method = RequestMethod.GET)
    public ModelAndView classZb(@Param("rowlimit") String rowlimit, @Param("timerange") String timerange, Model model)  {
        model.addAttribute("rowlimit", rowlimit);
        model.addAttribute("timerange", timerange);
        ModelAndView modelAndView=new ModelAndView("modules/manage/mybi/classzb.html");
        return modelAndView;
    }

    //====================================================================================================
    @RequestMapping(value = "mythirdclass",method = RequestMethod.GET)
    public String mythirdclass(@Param("rowlimit") String rowlimit,@Param("timerange") String timerange,
                              @Param("pic") String pic) throws UnsupportedEncodingException {
        String params;
        String pic_line="{\"datasource\": \"15__table\", \"viz_type\": \"line\", \"slice_id\": 94, \"url_params\": {}, \"granularity_sqla\": \"\\u65e5\\u671f\\u8fc7\\u6ee4\", \"time_grain_sqla\": null, \"time_range\": \"Last week\", \"metrics\": [\"\\u9500\\u552e\\u989d\"], \"adhoc_filters\": [], \"groupby\": [\"fl_name\"], \"limit\": 25, \"timeseries_limit_metric\": \"\\u9500\\u552e\\u989d\", \"order_desc\": true, \"contribution\": false, \"row_limit\": 10000, \"color_scheme\": \"bnbColors\", \"show_brush\": \"auto\", \"send_time_range\": false, \"show_legend\": true, \"rich_tooltip\": true, \"show_markers\": false, \"line_interpolation\": \"linear\", \"x_axis_label\": \"\\u65f6\\u95f4\", \"bottom_margin\": \"auto\", \"x_ticks_layout\": \"auto\", \"x_axis_format\": \"%Y-%m-%d\", \"x_axis_showminmax\": true, \"y_axis_label\": \"\\u9500\\u552e\\u989d\", \"left_margin\": \"auto\", \"y_axis_showminmax\": true, \"y_log_scale\": false, \"y_axis_format\": \".4r\", \"y_axis_bounds\": [null, null], \"rolling_type\": \"None\", \"comparison_type\": \"values\", \"resample_how\": null, \"resample_rule\": null, \"resample_fillmethod\": null, \"annotation_layers\": []}";
        String pic_word_cloud="{\"datasource\": \"15__table\", \"viz_type\": \"word_cloud\", \"slice_id\": 93, \"url_params\": {}, \"granularity_sqla\": \"ri_id\", \"time_grain_sqla\": \"P1D\", \"time_range\": \"Last week\", \"series\": \"fl_name\", \"metric\": \"\\u9500\\u552e\\u989d\", \"adhoc_filters\": [], \"row_limit\": 10000, \"size_from\": \"20\", \"size_to\": \"150\", \"rotation\": \"random\", \"color_scheme\": \"bnbColors\"}";
        String pic_pie="{\"datasource\": \"15__table\", \"viz_type\": \"pie\", \"slice_id\": 92, \"url_params\": {}, \"granularity_sqla\": \"ri_id\", \"time_grain_sqla\": \"P1D\", \"time_range\": \"Last week\", \"metric\": \"\\u9500\\u552e\\u989d\", \"adhoc_filters\": [], \"groupby\": [\"fl_name\"], \"row_limit\": 50, \"pie_label_type\": \"key_percent\", \"donut\": true, \"show_legend\": true, \"show_labels\": true, \"labels_outside\": true, \"color_scheme\": \"bnbColors\"}";
        String pic_treemap="{\"datasource\": \"15__table\", \"viz_type\": \"treemap\", \"slice_id\": 98, \"url_params\": {}, \"granularity_sqla\": \"ri_id\", \"time_grain_sqla\": \"P1D\", \"time_range\": \"Last week\", \"metrics\": [\"\\u9500\\u552e\\u989d\"], \"adhoc_filters\": [], \"groupby\": [\"fl_name\"], \"row_limit\": 250, \"color_scheme\": \"bnbColors\", \"treemap_ratio\": 1.618033988749895, \"number_format\": \".4r\"}";
        if(!StringUtils.hasText(pic)){
            params=pic_line;
        }else if(pic.equals("line")){
            params=pic_line;
        }else if(pic.equals("word_cloud")){
            params=pic_word_cloud;
        }else if(pic.equals("pie")){
            params=pic_pie;
        }else if(pic.equals("treemap")){
            params=pic_treemap;
        }else {
            params=pic_line;
        }
        ParameterEntity parameterEntity=new ParameterEntity();
        parameterEntity.setForm_data(params);
        UrlForTable urlForTable=new UrlForTable();

        if(StringUtils.hasText(timerange)){
            parameterEntity.setPars("time_range",timerange);

        }
        if(StringUtils.hasText(rowlimit)){
            parameterEntity.setPars("row_limit",Integer.parseInt(rowlimit));
        }
        parameterEntity.setHeight("800");
        return "redirect:"+urlForTable.getBasUrl()+parameterEntity.getParamsOrderByKey();
    }

    @RequestMapping(value = "thirdclass",method = RequestMethod.GET)
    public ModelAndView thirdclass(@Param("rowlimit") String rowlimit, @Param("timerange") String timerange,
                                  @Param("pic") String pic, Model model)  {
        model.addAttribute("rowlimit", rowlimit);
        model.addAttribute("timerange", timerange);
        model.addAttribute("pic", pic);
        ModelAndView modelAndView=new ModelAndView("modules/manage/mybi/thirdclass.html");
        return modelAndView;
    }

    //==========================================================================================================
    @RequestMapping(value = "mysecondclass",method = RequestMethod.GET)
    public String mysecondclass(@Param("rowlimit") String rowlimit,@Param("timerange") String timerange,
                               @Param("pic") String pic) throws UnsupportedEncodingException {
        String params;
        String pic_sanky="{\"datasource\": \"17__table\", \"viz_type\": \"sankey\", \"slice_id\": 99, \"url_params\": {}, \"granularity_sqla\": \"ri_id\", \"time_grain_sqla\": \"P1D\", \"time_range\": \"Last week\", \"groupby\": [\"fl_name\", \"xz_name\"], \"metric\": \"\\u9500\\u552e\\u989d\", \"adhoc_filters\": [], \"row_limit\": 10000, \"color_scheme\": \"bnbColors\"}";
        String pic_line="{\"datasource\": \"17__table\", \"viz_type\": \"line\", \"url_params\": {}, \"granularity_sqla\": \"ri_id\", \"time_grain_sqla\": \"P1D\", \"time_range\": \"Last week\", \"metrics\": [\"\\u9500\\u552e\\u989d\"], \"adhoc_filters\": [], \"groupby\": [\"fl_name\"], \"timeseries_limit_metric\": null, \"order_desc\": true, \"contribution\": false, \"row_limit\": 10000, \"color_scheme\": \"bnbColors\", \"show_brush\": \"auto\", \"send_time_range\": false, \"show_legend\": true, \"rich_tooltip\": true, \"show_markers\": false, \"line_interpolation\": \"linear\", \"x_axis_label\": \"\\u65f6\\u95f4\", \"bottom_margin\": \"auto\", \"x_ticks_layout\": \"auto\", \"x_axis_format\": \"%Y-%m-%d\", \"x_axis_showminmax\": false, \"y_axis_label\": \"\\u9500\\u552e\\u989d\", \"left_margin\": \"auto\", \"y_axis_showminmax\": false, \"y_log_scale\": false, \"y_axis_format\": \".4r\", \"y_axis_bounds\": [null, null], \"rolling_type\": \"None\", \"comparison_type\": \"values\", \"resample_how\": null, \"resample_rule\": null, \"resample_fillmethod\": null, \"annotation_layers\": []}";
        String pic_dist_bar="{\"datasource\": \"17__table\", \"viz_type\": \"dist_bar\", \"slice_id\": 103, \"url_params\": {}, \"granularity_sqla\": \"ri_id\", \"time_grain_sqla\": \"P1D\", \"time_range\": \"Last week\", \"metrics\": [\"\\u9500\\u552e\\u989d\"], \"adhoc_filters\": [], \"groupby\": [\"xz_name\"], \"columns\": [\"fl_name\"], \"row_limit\": 10000, \"contribution\": false, \"color_scheme\": \"bnbColors\", \"show_legend\": true, \"show_bar_value\": false, \"bar_stacked\": true, \"order_bars\": false, \"y_axis_format\": \".4r\", \"y_axis_label\": \"\\u9500\\u552e\\u989d\", \"show_controls\": false, \"x_axis_label\": \"\\u5730\\u57df\", \"bottom_margin\": \"auto\", \"x_ticks_layout\": \"auto\", \"reduce_x_ticks\": false}";
        String pic_treemap="{\"datasource\": \"17__table\", \"viz_type\": \"treemap\", \"url_params\": {}, \"granularity_sqla\": \"ri_id\", \"time_grain_sqla\": \"P1D\", \"time_range\": \"Last week\", \"metrics\": [\"\\u9500\\u552e\\u989d\"], \"adhoc_filters\": [], \"groupby\": [\"fl_name\", \"xz_name\"], \"row_limit\": 10000, \"color_scheme\": \"bnbColors\", \"treemap_ratio\": 1.618033988749895, \"number_format\": \".3s\"}";
        if(!StringUtils.hasText(pic)){
            params=pic_sanky;
        }else if(pic.equals("sanky")){
            params=pic_sanky;
        }else if(pic.equals("line")){
            params=pic_line;
        }else if(pic.equals("dist_bar")){
            params=pic_dist_bar;
        }else if(pic.equals("treemap")){
            params=pic_treemap;
        }else {
            params=pic_sanky;
        }
        ParameterEntity parameterEntity=new ParameterEntity();
        parameterEntity.setForm_data(params);
        UrlForTable urlForTable=new UrlForTable();

        if(StringUtils.hasText(timerange)){
            parameterEntity.setPars("time_range",timerange);

        }
        if(StringUtils.hasText(rowlimit)){
            parameterEntity.setPars("row_limit",Integer.parseInt(rowlimit));
        }

        return "redirect:"+urlForTable.getBasUrl()+parameterEntity.getParamsOrderByKey();
    }

    @RequestMapping(value = "secondclass",method = RequestMethod.GET)
    public ModelAndView secondclass(@Param("rowlimit") String rowlimit, @Param("timerange") String timerange,
                                   @Param("pic") String pic, Model model)  {
        model.addAttribute("rowlimit", rowlimit);
        model.addAttribute("timerange", timerange);
        model.addAttribute("pic", pic);
        ModelAndView modelAndView=new ModelAndView("modules/manage/mybi/secondclass.html");
        return modelAndView;
    }



    //=================================================================================================================

    @RequestMapping(value = "myfirstclass",method = RequestMethod.GET)
    public String myfirstclass(@Param("rowlimit") String rowlimit,@Param("timerange") String timerange,
                                @Param("pic") String pic) throws UnsupportedEncodingException {
        String params;
        String pic_sanky="{\"datasource\": \"18__table\", \"viz_type\": \"sankey\", \"slice_id\": 100, \"url_params\": {}, \"granularity_sqla\": \"ri_id\", \"time_grain_sqla\": \"P1D\", \"time_range\": \"Last week\", \"groupby\": [\"xz_name\", \"fl_name\"], \"metric\": \"\\u9500\\u552e\\u989d\", \"adhoc_filters\": [], \"row_limit\": 10000, \"color_scheme\": \"bnbColors\"}";
        String pic_line="{\"datasource\": \"18__table\", \"viz_type\": \"line\", \"url_params\": {}, \"granularity_sqla\": \"ri_id\", \"time_grain_sqla\": \"P1D\", \"time_range\": \"Last week\", \"metrics\": [\"\\u9500\\u552e\\u989d\"], \"adhoc_filters\": [], \"groupby\": [\"fl_name\"], \"timeseries_limit_metric\": null, \"order_desc\": true, \"contribution\": false, \"row_limit\": 10000, \"color_scheme\": \"bnbColors\", \"show_brush\": \"auto\", \"send_time_range\": true, \"show_legend\": true, \"rich_tooltip\": true, \"show_markers\": false, \"line_interpolation\": \"linear\", \"x_axis_label\": \"\\u65f6\\u95f4\", \"bottom_margin\": \"auto\", \"x_ticks_layout\": \"auto\", \"x_axis_format\": \"%Y-%m-%d\", \"x_axis_showminmax\": false, \"y_axis_label\": \"\\u9500\\u552e\\u989d\", \"left_margin\": \"auto\", \"y_axis_showminmax\": false, \"y_log_scale\": false, \"y_axis_format\": \".4r\", \"y_axis_bounds\": [null, null], \"rolling_type\": \"None\", \"comparison_type\": \"values\", \"resample_how\": null, \"resample_rule\": null, \"resample_fillmethod\": null, \"annotation_layers\": []}";
        String pic_dist_bar="{\"datasource\": \"18__table\", \"viz_type\": \"dist_bar\", \"slice_id\": 104, \"url_params\": {}, \"granularity_sqla\": \"ri_id\", \"time_grain_sqla\": \"P1D\", \"time_range\": \"Last week\", \"metrics\": [\"\\u9500\\u552e\\u989d\"], \"adhoc_filters\": [], \"groupby\": [\"xz_name\"], \"columns\": [\"fl_name\"], \"row_limit\": 10000, \"contribution\": false, \"color_scheme\": \"bnbColors\", \"show_legend\": true, \"show_bar_value\": false, \"bar_stacked\": true, \"order_bars\": false, \"y_axis_format\": \".4r\", \"y_axis_label\": \"\\u9500\\u552e\\u989d\", \"show_controls\": false, \"x_axis_label\": \"\\u5730\\u57df\", \"bottom_margin\": \"auto\", \"x_ticks_layout\": \"auto\", \"reduce_x_ticks\": false}";

        if(!StringUtils.hasText(pic)){
            params=pic_sanky;
        }else if(pic.equals("sanky")){
            params=pic_sanky;
        }else if(pic.equals("line")){
            params=pic_line;
        }else if(pic.equals("dist_bar")){
            params=pic_dist_bar;
        }else {
            params=pic_sanky;
        }
        ParameterEntity parameterEntity=new ParameterEntity();
        parameterEntity.setForm_data(params);
        UrlForTable urlForTable=new UrlForTable();

        if(StringUtils.hasText(timerange)){
            parameterEntity.setPars("time_range",timerange);

        }
        if(StringUtils.hasText(rowlimit)){
            parameterEntity.setPars("row_limit",Integer.parseInt(rowlimit));
        }

        return "redirect:"+urlForTable.getBasUrl()+parameterEntity.getParamsOrderByKey();
    }

    @RequestMapping(value = "firstclass",method = RequestMethod.GET)
    public ModelAndView firstclass(@Param("rowlimit") String rowlimit, @Param("timerange") String timerange,
                                    @Param("pic") String pic, Model model)  {
        model.addAttribute("rowlimit", rowlimit);
        model.addAttribute("timerange", timerange);
        model.addAttribute("pic", pic);
        ModelAndView modelAndView=new ModelAndView("modules/manage/mybi/firstclass.html");
        return modelAndView;
    }
}
