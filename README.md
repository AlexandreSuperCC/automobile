# automobile_backend
project of MO54A22
**Suitable for mobile phone and computer view**
## ATTENTION
!!! If you want to modify the code, PLEASE create a branch and start a pullrequest !!!

## run
### database
**the database is mysql, run file/mo_automobile.sql to construct locally**
in the concole :  
```sql
create database mo_automobile;
use mo_automobile;
source ${your absolute path}/mo_automobile.sql;
```


## main technique point/function
* function of cache, when requesting the same data, get directly from cache instead of the database 
* accomplish the restful style API
* make the same code into component (th:replace to use it) so avoid repeating myself
* minimize the type of script, (js/css)
* the background photo of login is changeable  
* lazy load the data of products instead of getting all of them at one time
* restore the password into database using md5 encryption for security purpose
* session/cookie is expired after 30 minutes, after that you should re-login
* restrict the number of wrong login, 3 times maximum and after that: block this ip and show:
[Vous avez entré le mauvais mot de passe plus de 3 fois, veuillez réessayer après 10 minutes]
using the technique of local cache to realize it
* set cookie in the browser, so close the page and reopen, your account is still alive
* suitable for mobile phone and computer view


