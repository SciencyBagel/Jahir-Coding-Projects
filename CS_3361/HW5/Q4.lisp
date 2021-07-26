;gnu clisp  2.49.60

(defun max1 (a b c)
  (setq x -9999)

  (when (> a x) (setq x a))
  (when (> b x) (setq x b))
  (when (> c x) (setq x c))

  (return-from max1 x)
)
(write (max1 1 2 3))
(terpri)

(defun foo (x L)
  (setq y nil)

  (setq M (list (member x L)))

    ;(setq size (length M))

  (if (null (car M)) (setq y nil) (setq y t))

  (return-from foo y)
)
(write (foo 2 (list 2 3 4 5)))
(terpri)
