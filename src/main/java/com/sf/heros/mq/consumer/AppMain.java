package com.sf.heros.mq.consumer;

import com.fdc.home.dec.service.inter.domain.define.Page;
import com.fdc.home.dec.service.inter.domain.generation.DecPrice;
import com.fdc.home.dec.service.inter.service.DecOfferManageService;
import com.sf.heros.mq.consumer.service.ElasticsearchService;
import com.sf.heros.mq.consumer.utils.APP;
import com.sf.heros.mq.consumer.vo.DecPriceVo;
import com.sf.heros.mq.consumer.vo.TaskInfo;
import org.apache.log4j.Logger;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AppMain {

	private static final Logger logger = Logger.getLogger(AppMain.class);

	public void start() {
		ClassPathXmlApplicationContext context = null;
		try {
			context = new ClassPathXmlApplicationContext("classpath:app.xml");
		} catch (Exception e) {
			logger.error("An error occurred, applicationContext will close.", e);
			if (context != null) {
				context.close();
			}
			context = null;
			logger.error(APP.CLOSED_MSG);
		}
	}

	/**
	 * 插入
	* @author 高国藩
	* @date 2015年6月16日 上午10:14:21
	 */
	@Test
	public void insertNo() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:app.xml");
		ElasticsearchService service = context
				.getBean(ElasticsearchService.class);
		List<TaskInfo> taskInfoList = new ArrayList<TaskInfo>();
		for (int i = 0; i < 20; i++) {
			taskInfoList.add(new TaskInfo(String.valueOf((i + 5)), i + 5, "高国藩"
					+ i, "taskArea", "taskTags", i + 5, "1996-02-03", "霍华德"));
		}
		service.insertOrUpdateTaskInfo(taskInfoList);
	}

    @Test
    public void insertDecPrice() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:app.xml");
        ElasticsearchService service = context.getBean(ElasticsearchService.class);
        DecOfferManageService decOfferManageService= (DecOfferManageService) context.getBean("decOfferManageService");
        List<DecPriceVo> decPriceVoList = new ArrayList<DecPriceVo>();
        DecPriceVo decPriceVo;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Page page=new Page();
        paramMap.put("start", page.getStart());
        page.setPagesize(200);
        paramMap.put("pagesize", page.getPagesize());
        paramMap.put("checkState",page.getCheckState());
        paramMap.put("searchType",page.getSearchType());
        paramMap.put("searchWord",page.getSearchWord());
        List<DecPrice> decPriceList=decOfferManageService.getDecPriceList(paramMap);
        for (DecPrice decPrice: decPriceList) {
            decPriceVo=new DecPriceVo();
            decPriceVo.setPid(decPrice.getPid());
            decPriceVo.setUserId(decPrice.getUserId());
            decPriceVo.setUserTel(decPrice.getUserTel());
            decPriceVo.setUserQq(decPrice.getUserQq());
            decPriceVo.setNickName(decPrice.getNickName());
            decPriceVo.setCityId(decPrice.getCityId());
            decPriceVo.setDistrictId(decPrice.getDistrictId());
            decPriceVo.setStreetId(decPrice.getStreetId());
            decPriceVo.setResidentialName(decPrice.getResidentialName());
            decPriceVo.setBaseId(decPrice.getBaseId());
            decPriceVo.setHouseArea(decPrice.getHouseArea());
            decPriceVo.setDecPrice(decPrice.getDecPrice());
            decPriceVo.setCheckState(decPrice.getCheckState());
            decPriceVo.setViewCount(decPrice.getViewCount());
            decPriceVo.setDownloadCount(decPrice.getDownloadCount());
            decPriceVo.setGmtCreated(decPrice.getGmtCreated());
            decPriceVo.setGmtModified(decPrice.getGmtModified());
            decPriceVo.setCreatedBy(decPrice.getCreatedBy());
            decPriceVo.setModifiedBy(decPrice.getModifiedBy());
            decPriceVoList.add(decPriceVo);
        }
        service.insertOrUpdateDecPrice(decPriceVoList);
    }
	@Test
	public void delete() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:app.xml");
		ElasticsearchService service = context
				.getBean(ElasticsearchService.class);
		List<TaskInfo> taskInfoList = new ArrayList<TaskInfo>();
		for (int i = 5; i < 25; i++) {
			service.deleteById(i+"",TaskInfo.class);
		}
	}

	/**
	 * 查询
	* @author 高国藩
	* @date 2015年6月16日 上午10:14:21
	 */
	@Test
	public void serchNo() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:app.xml");
		com.sf.daidongxi.web.service.ElasticsearchService service = (com.sf.daidongxi.web.service.ElasticsearchService) context
				.getBean("es");
		List<Map<String, Object>> al = service.queryForObject("task_info",
				new String[]{"taskContent", "taskArea"}, "高国藩", "taskArea", SortOrder.DESC,
				0, 2);

		for (int i = 0; i < al.size(); i++) {
			System.out.println(al.get(i));
		}
	}

    @Test
    public void searchDecPrice() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:app.xml");
        com.sf.daidongxi.web.service.ElasticsearchService service = (com.sf.daidongxi.web.service.ElasticsearchService) context
                .getBean("es");
        List<Map<String, Object>> al = service.queryForObject("dec_price",
                new String[]{"residentialName"}, "大唐都", "residentialName", SortOrder.DESC,
                0, 20);

        for (int i = 0; i < al.size(); i++) {
            System.out.println(al.get(i));
        }
    }

//	@Test
//	public void search() {
//		QueryStringQueryBuilder queryString = QueryBuilders.queryStringQuery("\""
//				+ "高国藩" + "\"");
//		SearchQuery searchQuery = new NativeSearchQueryBuilder()
//				.withQuery(queryString().field("id"))
//				.build();
//		Page<SampleEntity> sampleEntities = elasticsearchTemplate.queryForPage(searchQuery, SampleEntity.class);
//
//	}
	
	/**
	 * filter查询
	* @author 高国藩
	* @date 2015年6月16日 上午10:14:21
	 */
//	@Test
//	public void serchFilter() {
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
//				"classpath:app.xml");
//		com.sf.daidongxi.web.service.ElasticsearchService service = (com.sf.daidongxi.web.service.ElasticsearchService) context
//				.getBean("es");
//		List<Map<String, Object>> al = service.queryForObjectForElasticSerch("task_info", "taskContent", "高",19,20);
//
//		for (int i = 0; i < al.size(); i++) {
//			System.out.println(al.get(i));
//		}
//
//	}
}
