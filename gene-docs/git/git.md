# git

[TOC]

## git基本命令

### 初始化git仓库和添加远程仓库

    在目录下执行命令：git init
    克隆git仓库：git clone git仓库地址
### 添加全局配置信息

    git config --global user.name "Adam"
    git config --global user.email "Adam@example.com"
    git config --list                                   查看全局配置信息
### 常用命令

    git status                                          查看本地代码状态
    git commit -m "notes"                               提交并添加注释
    git remote rename x y                               将x仓库重命名为y
### repository

```
git remote -v                             			查看远程仓库
git remote add [name] [repository-url]				添加远程仓库
git remote rm [name]								删除远程仓库
git remote rename x y								将x仓库重命名为y 
git remote set-url origin new-repository-url		修改远程仓库地址
git pull [remoteName] [localBranchName]				拉取远程仓库
git push [remoteName] [localBranchName]				推送到远程仓库                
```

### branch

    git branch                                          查看本地分支
    git branch -a                                       查看本地和远程分支
    git branch branchName                               在本地创建branchName分支
    git checkout -b branchName                          在本地创建branchName分支并切换到该分支
    git branch -d branchName                            删除本地branchName分支
    git push repositoryName branchName                  将branchName分支推送到repositoryName仓库
    git push repositoryName --delete branchName         删除repositoryName仓库的branchName分支	
### add

    git add files                                       将files添加到暂存区
    git add -A .                                        来一次添加所有改变的文件。
    git add -A                                          表示添加所有内容
    git add .                                           表示添加新文件和编辑过的文件不包括删除的文件
    git add -u                                          表示添加编辑或者删除的文件，不包括新添加的文件。
### checkout

    git checkout files                                  撤销本地指定files的修改（该修改还未添加到暂区）
    git checkout .                                      撤销本地所有的修改（未添加到暂存区的所有修改）
    git checkout branchName                             切换到branchName分支，如果branchName不存在
    git checkout -b branchName                          在本地创建branchName分支并切换到该分支
### diff

    git diff file                                       比较当前文件和暂存区文件差异
    git diff HASH1 HASH2                                比较两次提交之间的差异
    git diff branch1 branch                             在两个分支之间比较
    git diff --staged                                   比较暂存区和版本库差异
    git diff --cached                                   比较暂存区和版本库差异
    git diff --stat                                     仅仅比较统计信息
### reset

    git reset .                                         将所有修改从暂存区中撤销至本地修改
    git reset files                                     将files的修改从暂存区中撤销至本地修改
    git reset --hard HASH                               本地代码撤销至HASH版本且不保留修改
    git reset --soft HASH                               本地代码撤销至HASH版本并保留修改
### stash

关于stash的命令

    git stash                                           暂存
    git stash list                                      列所有stash
    git stash apply                                     恢复暂存的内容
    git stash drop                                      删除暂存区
### submodule

    git submodule init
    git submodule update
    git submodule foreach "git command"                 递归给子模块执行git command命令
## git常见的设置

### 存储用户名与密码

.gitconfig 文件中添加 

```
[credential]    
    helper = store
```

或者在git bash 中执行

```
git config --global credential.helper store
```

### 配置提交模板