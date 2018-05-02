/*
 * Copyright (C) 2018 Andronicus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.andronicus.todomvp.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.annotation.NonNull;

import java.util.List;

import io.andronicus.todomvp.data.Task;

@Dao
public interface TasksDao {

    @Query("SELECT * FROM Tasks")
    List<Task> getAllTasks();

    @Query("SELECT * FROM Tasks WHERE entryid = :taskId")
    Task getTaskById(@NonNull String taskId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTask(Task task);

    @Update
    int updateTask(Task task);

    @Query("UPDATE tasks SET completed = :completed WHERE entryid = :taskId")
    void updateCompleted(@NonNull boolean completed, @NonNull String taskId);

    /**
     * Delete a task by id.
     *
     * @return the number of tasks deleted. This should always be 1.
     */
    @Query("DELETE FROM Tasks WHERE entryid = :taskId")
    int deleteTaskById(String taskId);

    /**
     * Delete all tasks.
     */
    @Query("DELETE FROM Tasks")
    void deleteTasks();

    /**
     * Delete all completed tasks from the table.
     *
     * @return the number of tasks deleted.
     */
    @Query("DELETE FROM Tasks WHERE completed = 1")
    int deleteCompletedTasks();
}
