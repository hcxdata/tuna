/**
  Default users used id
 */
MERGE INTO `sequences` (`sequence`, `last_used`)
  KEY (`sequence`)
  VALUES('users', 10001);

/**
  Default Users
  Default password is password
 */
MERGE INTO `users` (`id`, `username`, `password`,
                    `nosalt_password`, `salt`,
                    `phone`, `type`, `status`, `registered_at`)
  VALUES(1, 'admin', '58b6747b7802e2902901d60827266fd2151de7385c2ef491f1f9988ae75bcf32cde1698c55b36034',
         '4db302395ab91fffc0cfbfae2dee01fd67712811a4657f978ff1c42e1b170f6e', '67d73285bfeddf54',
         'rPEVDZ8ykEcFguryLNj+QA==', 1, 0, '2018-10-09 13:06:00'); 

/**
 * User profile data
 */
MERGE INTO `user_profiles` (`uid`, `real_name`, `nickname`, `gender`, `birthday`, `signature`)
 VALUES (1, '管理员', '智能制造2025', 1, '1977-1-1', '中国智能制造2025崛起');

