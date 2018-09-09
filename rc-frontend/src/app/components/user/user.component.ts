import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model';
import {UIContext} from '../../ui.context';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  displayedColumns: string[] = ['fullName', 'username'];

  users: User[];
  constructor(
    private uiContext: UIContext,
    private userService: UserService
  ) {}

  ngOnInit() {
    this.uiContext.setTittle("Usuarios");

    this.userService.findAll()
      .subscribe( data => {
        this.users = data;
      });
  }

  deleteUser(user: User): void {
    /*
    this.userService.deleteUser(user)
      .subscribe( data => {
        this.users = this.users.filter(u => u !== user);
      })
      */
  };

}
