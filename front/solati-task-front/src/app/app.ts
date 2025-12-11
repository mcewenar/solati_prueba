import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TaskService } from './task.service';
import { Task } from './task.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {

  username = '';
  password = '';
  error: string | null = null;
  loggedIn = false;

  tasks: Task[] = [];

  constructor(private taskService: TaskService) {}

  login() {
    this.error = null;

    this.taskService.getTasks(this.username, this.password).subscribe({
      next: tasks => {
        this.tasks = tasks;
        this.loggedIn = true;
      },
      error: err => {
        console.error(err);
        this.error = 'Invalid username or password';
        this.loggedIn = false;
        this.tasks = [];
      }
    });
  }

  logout() {
    this.loggedIn = false;
    this.username = '';
    this.password = '';
    this.error = null;
    this.tasks = [];
  }
}
