// src/app/task/task.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Task } from './task.model';

@Injectable({ providedIn: 'root' })
export class TaskService {

  private readonly apiUrl = 'http://localhost:8080/api/task';

  constructor(private http: HttpClient) {}

  getTasks(username: string, password: string): Observable<Task[]> {
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(`${username}:${password}`)
    });

    return this.http.get<Task[]>(this.apiUrl, { headers });
  }
}
