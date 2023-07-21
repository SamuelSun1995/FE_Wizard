package com.yifan.fewizard.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.yifan.fewizard.database.entity.FuelEntity


@Dao
interface FuelDao {

    //查询所有数据
    @Query("Select * from fuelentity")
    fun getAll(): List<FuelEntity>

    //删除全部数据
    @Query("DELETE from fuelentity")
    fun deleteAll()

    //一次插入单条数据 或 多条
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg fuelEntity: FuelEntity)

    //一次删除单条数据 或 多条
    @Delete
    fun delete(vararg fuelEntity: FuelEntity)

    //一次更新单条数据 或 多条
    @Update
    fun update(vararg fuelEntity: FuelEntity)

    //根据字段去查找数据
    @Query("SELECT * FROM fuelentity WHERE id= :id")
    fun getPersonById(id: Int): FuelEntity

    //一次查找多个数据
    @Query("SELECT * FROM fuelentity WHERE id IN (:ids)")
    fun loadAllByIds(ids: List<Int>): List<FuelEntity>
}

/**
 * 这里唯一特殊的就是@Insert。其有一段介绍：对数据库设计时，不允许重复数据的出现。否则，必然造成大量的冗余数据。实际上，难免会碰到这个问题：冲突。当我们像数据库插入数据时，该数据已经存在了，必然造成了冲突。该冲突该怎么处理呢？在@Insert注解中有conflict用于解决插入数据冲突的问题，其默认值为OnConflictStrategy.ABORT。对于OnConflictStrategy而言，它封装了Room解决冲突的相关策略。
 * OnConflictStrategy.REPLACE：冲突策略是取代旧数据同时继续事务
 * OnConflictStrategy.ROLLBACK：冲突策略是回滚事务
 * OnConflictStrategy.ABORT：冲突策略是终止事务
 * OnConflictStrategy.FAIL：冲突策略是事务失败
 * OnConflictStrategy.IGNORE：冲突策略是忽略冲突
 * 这里比如在插入的时候我们加上了OnConflictStrategy.REPLACE，那么往已经有uid=1的person表里再插入uid =1的person数据，那么新数据会覆盖就数据。如果我们什么都不加，那么久是默认的OnConflictStrategy.ABORT，重复上面的动作，你会发现，程序崩溃了。也就是上面说的终止事务。其他大家可以自己试试
 */