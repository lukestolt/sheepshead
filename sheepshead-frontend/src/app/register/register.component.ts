import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';
import { User } from '../models';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  private username: string;
  private password: string;
  private loading: boolean;
  constructor(
    private userService: UserService,
    private router: Router,
    private authenticationService: AuthenticationService,
  ) { 
     // redirect to home if already logged in
     if (this.authenticationService.currentUserValue) { 
      this.router.navigate(['/home']);
    }
  }

  ngOnInit() {
  }

  loginClick(): void {
    if(this.isValidForm()) {
      console.log(this.username);
      console.log(this.password);
      this.loading = true;
      const user = this.createUser();
      this.authenticationService.login(user);
      // this.userService.register(user)
      //       // .pipe(first())
      //       .subscribe(
      //           data => {
      //               console.log('Registration successful', true);
      //               console.log(data);
      //               this.router.navigate(['/home']);
      //           },
      //           error => {
      //             console.log(error)
      //               this.loading = false;
      //               console.error('Invalid login');
      //           });

    }
  }

  createUser(): User {
    const u: User = {username: this.username, password: this.password };
    return u;
  }

  private isValidForm(): boolean {
    if (this.username && this.password) return true;
    return false;
  }
}
