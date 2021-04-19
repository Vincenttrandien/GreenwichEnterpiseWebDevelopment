// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

const API = {
  HTTP_SCHEMA: 'http',
  PREFIX: '://',
  API_DIENHAU: '172.16.1.206',
  API_BANTHAN: 'localhost',
};

export const environment = {
  production: false,
  HOME_PAGE: '/home-page',
  API_DIEN_HAU: API.HTTP_SCHEMA + API.PREFIX + API.API_DIENHAU + ':8080',
  API_BAN_THAN: API.HTTP_SCHEMA + API.PREFIX + API.API_BANTHAN + ':8080',
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
