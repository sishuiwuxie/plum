package com.ibeetl.admin.core.service;

import com.ibeetl.admin.core.entity.CoreFunction;
import com.ibeetl.admin.core.entity.CoreMenu;
import com.ibeetl.admin.core.gen.model.Attribute;
import com.ibeetl.admin.core.gen.model.Entity;
import com.ibeetl.admin.core.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.beetl.sql.core.JavaType;
import org.beetl.sql.core.NameConversion;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.db.ClassDesc;
import org.beetl.sql.core.db.ColDesc;
import org.beetl.sql.core.db.MetadataManager;
import org.beetl.sql.core.db.TableDesc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 代码生成，用于根据表或者视图生成entity，mapper，service，conroller
 * 未来可以生成swagger api，界面
 *
 * @author xiandafu
 */
@Service
public class CoreCodeGenService {
    private final Logger log = LoggerFactory.getLogger( this.getClass() );
    @Autowired
    SQLManager sqlManager;
    @Autowired
    CorePlatformService platformService;

    public void refresh() {
        sqlManager.refresh();
    }

    public List<Entity> getAllEntityInfo() {
        MetadataManager meta = sqlManager.getMetaDataManager();
        Set<String> set = meta.allTable();
        List<Entity> list = new ArrayList<Entity>();
        for (String table : set) {
            list.add( getEntitySimpleInfo( table ) );
        }
        return list;
    }

    public Entity getEntitySimpleInfo(String table) {
        MetadataManager meta = sqlManager.getMetaDataManager();
        NameConversion nc = sqlManager.getNc();
        TableDesc tableDesc = meta.getTable( table );
        if (tableDesc == null) {
            return null;
        }
        ClassDesc classDesc = tableDesc.getClassDesc( nc );
        Entity e = new Entity();
        e.setName( nc.getClassName( table ) );
        e.setComment( tableDesc.getRemark() );
        e.setTableName( table );
        return e;
    }

    public Entity getEntityInfo(String table) {
        MetadataManager meta = sqlManager.getMetaDataManager();
        NameConversion nc = sqlManager.getNc();
        TableDesc tableDesc = meta.getTable( table );
        if (tableDesc == null) {
            return null;
        }
        ClassDesc classDesc = tableDesc.getClassDesc( nc );
        Entity e = new Entity();
        e.setName( nc.getClassName( table ) );
        e.setComment( tableDesc.getRemark() );
        e.setTableName( table );
        e.setCode( getEntityCode( e.getName() ) );

        Set<String> cols = tableDesc.getCols();
        ArrayList<Attribute> attrs = new ArrayList<Attribute>();
        int i = 1;
        for (String col : cols) {
            ColDesc desc = tableDesc.getColDesc( col );
            Attribute attr = new Attribute();
            attr.setColName( col );
            attr.setName( nc.getPropertyName( col ) );
            if (tableDesc.getIdNames().contains( col )) {
                //TODO,代码生成实际上用了一个Id，因此具备联合主键的，不应该生成代码
                attr.setId( true );
                e.setIdAttribute( attr );
            }
            attr.setComment( desc.remark );
            String type = JavaType.getType( desc.sqlType, desc.size, desc.digit );
            if (type.equals( "Double" )) {
                type = "BigDecimal";
            }
            if (type.equals( "Timestamp" )) {
                type = "Date";
            }
            attr.setJavaType( type );
            setGetDisplayName( attr );
            attrs.add( attr );

        }
        e.setList( attrs );

        return e;
    }

    /**
     * @param data
     * @param urlBase
     * @return 增删改查中的查
     */
    public String insertFunction(Entity data, String urlBase) {
        String prefix = urlBase.replace( '/', '.' );
        String functionCode = prefix + "." + data.getCode();
        String indexFunctionCode = functionCode + ".query";
        CoreFunction query = new CoreFunction();
        query.setCode( indexFunctionCode );
        Object o = sqlManager.templateOne( query );
        if (o != null) {
            log.warn( "indexFunction:" + indexFunctionCode + "  已经存在" );
            return null;
        }

        //设置父功能点
        CoreFunction rootFunction = new CoreFunction();
        String parentId = UUIDUtil.uuid();
        rootFunction.setId( parentId );
        rootFunction.setName( data.getDisplayName() );
        rootFunction.setCode( functionCode );
        rootFunction.setCreateTime( new Date() );
        rootFunction.setParentId( "" );
        rootFunction.setType( "FN0" );
        rootFunction.setAccessUrl( "/" + urlBase + "/" + data.getCode() + "/index.do" );
        sqlManager.insert( rootFunction, true );

        //设置曾删改查功能点
        CoreFunction indexFunction = new CoreFunction();
        indexFunction.setId( UUIDUtil.uuid() );
        indexFunction.setName( "查询" + data.getDisplayName() );
        indexFunction.setCode( indexFunctionCode );
        indexFunction.setCreateTime( new Date() );
        indexFunction.setParentId( parentId );
        indexFunction.setAccessUrl( "/" + urlBase + "/" + data.getCode() + "/index.do" );
        //设置为查询功能
        indexFunction.setType( "FN1" );
        sqlManager.insert( indexFunction, true );

        CoreFunction upateFunction = new CoreFunction();
        String updateFunctonCode = functionCode + ".edit";
        upateFunction.setId( UUIDUtil.uuid() );
        upateFunction.setName( "修改" + data.getDisplayName() );
        upateFunction.setCode( updateFunctonCode );
        upateFunction.setCreateTime( new Date() );
        upateFunction.setParentId( parentId );
        upateFunction.setType( "FN0" );
        sqlManager.insert( upateFunction, true );

        CoreFunction addFunction = new CoreFunction();
        String addFunctionCode = functionCode + ".add";
        addFunction.setId( UUIDUtil.uuid() );
        addFunction.setName( "添加" + data.getDisplayName() );
        addFunction.setCode( addFunctionCode );
        addFunction.setCreateTime( new Date() );
        addFunction.setParentId( parentId );
        addFunction.setType( "FN0" );
        sqlManager.insert( addFunction, true );

        CoreFunction delFunction = new CoreFunction();
        String delFunctionCode = functionCode + ".delete";
        delFunction.setId( UUIDUtil.uuid() );
        delFunction.setName( "删除" + data.getDisplayName() );
        delFunction.setCode( delFunctionCode );
        delFunction.setCreateTime( new Date() );
        delFunction.setParentId( parentId );
        delFunction.setType( "FN0" );
        sqlManager.insert( delFunction, true );

        //刷新缓存
        platformService.clearFunctionCache();

        return parentId;
    }

    public boolean insertMenu(String functionId, Entity data, String urlBase) {
        CoreMenu query = new CoreMenu();
        query.setCode( "代码生成导航" );
        query.setType( "MENU_N" );
        CoreMenu menu = this.sqlManager.templateOne( query );
        if (menu == null) {
            log.warn( "未找到对应的父菜单:" + query.getCode() );
            return false;
        }
        String parentId = menu.getId();
        CoreMenu newMenu = new CoreMenu();
        newMenu.setId( UUIDUtil.uuid() );
        newMenu.setCode( data.getName() + ".Manager" );
        newMenu.setName( data.getDisplayName() + "管理" );
        newMenu.setParentMenuId( parentId );
        newMenu.setFunctionId( functionId );
        newMenu.setType( "MENU_M" );
        //任意设置一个顺序
        newMenu.setSeq( 3 );
        this.sqlManager.insert( newMenu );
        this.platformService.clearMenuCache();
        return true;
    }

    //根据类名提供一个变量名
    private String getEntityCode(String s) {
        //找到最后一个大写字母，以此为变量名
        if (Character.isLowerCase( s.charAt( 0 ) )) {
            return s;
        } else {
            return (new StringBuilder())
                    .append( Character.toLowerCase( s.charAt( 0 ) ) )
                    .append( s.substring( 1 ) ).toString();
        }

    }

    /*根据数据库注释来判断显示名称*/
    private void setGetDisplayName(Attribute attr) {
        String comment = attr.getComment();
        if (StringUtils.isEmpty( comment )) {
            attr.setDisplayName( attr.getName() );
            return;
        }
        String displayName = null;
        int index = comment.indexOf( "," );
        if (index != -1) {
            displayName = comment.substring( 0, index );
            attr.setDisplayName( displayName );
        } else {
            attr.setDisplayName( comment );
        }
    }

}
