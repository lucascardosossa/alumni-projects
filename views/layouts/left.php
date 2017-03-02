<?php
use webvimark\modules\UserManagement\components\GhostMenu;
use webvimark\modules\UserManagement\UserManagementModule;

?>
<aside class="main-sidebar">

    <section class="sidebar">

        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="<?= $directoryAsset ?>/img/user2-160x160.jpg" class="img-circle" alt="User Image"/>
            </div>
            <div class="pull-left info">
                <p><?= \webvimark\modules\UserManagement\models\User::getCurrentUser()->username ?></p>
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>


        <?=
        GhostMenu::widget([
            'encodeLabels' => false,
            'activateParents' => true,
            'options' => ['class' => 'sidebar-menu'],
            'submenuTemplate' => "\n<ul class='treeview-menu' {show}>\n{items}\n</ul>\n",
            'items' => [
                [
                    'label' => '<i class="fa fa-users"></i> ' . 'Management Control Access',
                    'url' => '#',
                    'items' => [
                        ['label' => '<i class="fa fa-circle-o"></i> ' . UserManagementModule::t('back', 'Users'), 'url' => ['/user-management/user/index']],
                        ['label' => '<i class="fa fa-circle-o"></i> ' . UserManagementModule::t('back', 'Roles'), 'url' => ['/user-management/role/index']],
                        ['label' => '<i class="fa fa-circle-o"></i> ' . UserManagementModule::t('back', 'Permissions'), 'url' => ['/user-management/permission/index']],
                        ['label' => '<i class="fa fa-circle-o"></i> ' . UserManagementModule::t('back', 'Permission groups'), 'url' => ['/user-management/auth-item-group/index']],
                        ['label' => '<i class="fa fa-circle-o"></i> ' . UserManagementModule::t('back', 'Visit log'), 'url' => ['/user-management/user-visit-log/index']],
                    ]
                ],
                [
                    'label' => '<i class="fa fa-building"></i> ' . 'Opportunities',
                    'url' => '#',
                    'items' => [
                        ['label' => '<i class="fa fa-circle-o"></i> Gerenciar', 'url' => ['/opportunity/']],
                        ['label' => '<i class="fa fa-circle-o"></i> Novo', 'url' => ['/opportunity/create']],
                    ]
                ],
                [
                    'label' => '<i class="fa fa-handshake-o"></i> ' . 'Partners',
                    'url' => '#',
                    'items' => [
                        ['label' => '<i class="fa fa-circle-o"></i> Gerenciar', 'url' => ['/partner']],
                        ['label' => '<i class="fa fa-circle-o"></i> Novo', 'url' => ['/partner/create']],
                    ]
                ],
                [
                    'label' => '<i class="fa fa-cart-plus"></i> ' . 'Products',
                    'url' => '#',
                    'items' => [
                        ['label' => '<i class="fa fa-circle-o"></i> Gerenciar', 'url' => ['/product']],
                        ['label' => '<i class="fa fa-circle-o"></i> Novo', 'url' => ['/product/create']],
                    ]
                ],
                [
                    'label' => '<i class="fa fa-suitcase"></i> ' . 'POC',
                    'url' => '#',
                    'items' => [
                        ['label' => '<i class="fa fa-circle-o"></i> Gerenciar', 'url' => ['/poc']],
                        ['label' => '<i class="fa fa-circle-o"></i> Novo', 'url' => ['/poc/create']],
                    ]
                ],
                [
                    'label' => '<i class="fa fa-eye"></i> ' . 'Finder',
                    'url' => '#',
                    'items' => [
                        ['label' => '<i class="fa fa-circle-o"></i> Gerenciar', 'url' => ['/finder']],
                        ['label' => '<i class="fa fa-circle-o"></i> Novo', 'url' => ['/finder/create']],
                    ]
                ],
            ],
        ]);
        ?>
    </section>

</aside>
